import zio.http.{Server, ServerConfig}
import zio.http.*
import zio.http.model.Method.GET

object Main extends zio.ZIOAppDefault {
  
  val app = Http.collect {
    case GET -> !! / "api" / "sample" => Response.ok
  }

  override def run = Server
    .serve(app)
    .exitCode
    .provide(
      ServerConfig.live(ServerConfig.default.port(9092)),
      Server.live
    )
}
