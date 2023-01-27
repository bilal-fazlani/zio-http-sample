import zio.http.middleware.Cors
import zio.http.model.{HttpError, Status}
import zio.http.api.*
import zio.http.{Server, ServerConfig, ZClient}
import zio.http.HttpApp

object Main extends zio.ZIOAppDefault {

  val cors = Middleware.cors(zio.http.middleware.Cors.CorsConfig())

  val serviceSpec = (SampleApi.spec.toServiceSpec)
    .middleware(MiddlewareSpec.cors)

  val app =
    serviceSpec
      .toHttpApp(SampleEndpoint.endpoint, cors)
      .withDefaultErrorResponse

  // accessible at
  // http://localhost:9091/api/sample

  override def run = Server
    .serve(app)
    .exitCode
    .provide(
      ServerConfig.live(ServerConfig.default.port(9091)),
      SampleService.live,
      Server.live
    )
}
