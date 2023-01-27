import zio.*

enum SampleErrors:
  case InvalidInput // HTTP 400
  case NotAllowed // HTTP 403

trait SampleService:
  def serve(): IO[SampleErrors, SampleOutput]

object SampleService:
  val live = ZLayer.succeed(SampleServiceLive)

case object SampleServiceLive extends SampleService:
  def serve(): IO[SampleErrors, SampleOutput] = ZIO.succeed(SampleOutput(2))
