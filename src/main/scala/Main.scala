import zio.http.middleware.Cors
import zio.http.model.{HttpError, Status}
import zio.http.api.*
import zio.http.{Server, ServerConfig, ZClient}
import zio.http.HttpApp
import zio.http.Response

object Main extends zio.ZIOAppDefault {

  val cors = Middleware.cors(zio.http.middleware.Cors.CorsConfig())

  val serviceSpec = (SampleApi.spec.toServiceSpec)
    .middleware(MiddlewareSpec.cors)

  val httpApp =
    serviceSpec
      .toHttpApp(SampleEndpoint.endpoint, cors)
      .mapError {
        case SampleErrors.InvalidInput =>
          Response.fromHttpError(HttpError.BadRequest("invalid request"))
        case SampleErrors.NotAllowed =>
          Response.fromHttpError(HttpError.Forbidden("no allowed"))
      }

  // accessible at
  // http://localhost:9091/api/sample

  override def run = Server
    .serve(httpApp)
    .exitCode
    .provide(
      ServerConfig.live(ServerConfig.default.port(9091)),
      SampleService.live,
      Server.live
    )
}
