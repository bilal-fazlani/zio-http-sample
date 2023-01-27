import zio.http.api.EndpointSpec
import zio.http.model.Status
import zio.http.model.Method
import zio.http.api.*
import zio.http.api.RouteCodec.*
import zio.http.api.QueryCodec.*
import zio.http.api.CodecType.Route
import zio.schema.{Schema, DeriveSchema}

case class SampleInput(name: String)
object SampleInput {
  given Schema[SampleInput] = DeriveSchema.gen
}

case class SampleOutput(id: Int)
object SampleOutput {
  given Schema[SampleOutput] = DeriveSchema.gen
}

object SampleApi {
  val spec =
    EndpointSpec.get(RouteCodec.literal("api") / "sample").out[SampleOutput]
}
