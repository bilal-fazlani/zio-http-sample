import zio.*

object SampleEndpoint {
  val endpoint = SampleApi.spec.implement(input =>
    ZIO.serviceWithZIO[SampleService](_.serve(input))
  )
}
