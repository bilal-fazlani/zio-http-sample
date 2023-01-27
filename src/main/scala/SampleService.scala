import zio.*

enum SampleErrors:
  case InvalidInput // HTTP 400
  case NotAllowed // HTTP 403

trait SampleService:
  def serve(): IO[SampleErrors, SampleOutput]

object SampleService:
  val live = ZLayer.succeed(SampleServiceLive)

case object SampleServiceLive extends SampleService:
  def serve(): IO[SampleErrors, SampleOutput] = for {
    random <- ZIO.randomWith(_.nextIntBetween(0, 3))
    result <- random match
      case 0 => ZIO.fail(SampleErrors.InvalidInput)
      case 1 => ZIO.fail(SampleErrors.NotAllowed)
      case 2 => ZIO.succeed(SampleOutput(2))
  } yield result
