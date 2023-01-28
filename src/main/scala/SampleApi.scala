import zio.http.api.EndpointSpec
import zio.http.api.*
import zio.http.api.RouteCodec.*
import zio.http.api.QueryCodec.*
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
    EndpointSpec
      .get("api" / "sample")
      .in[SampleInput](body)
      .out[SampleOutput]
}

package zio.http.api {
  def body[A: Schema]: HttpCodec[CodecType.Body, A] =
    HttpCodec.Body(summon[Schema[A]])
}
