package serde
import akka.util.ByteString

case class Formatter(
  ident: Boolean,
  space: Boolean
)

object Formatter {
  val pretty = Formatter(ident = true, space = true)
  val condensed = Formatter(ident = false, space = false)
}

object JsonSerializer {
  def serialize[A: Serialize](a: A, formatter: Formatter = Formatter.pretty): ByteString = {
    val serializer = new internal.InternalJsonSerializer(formatter)
    Serialize[A].serialize(a, serializer)
    serializer.result()
  }

}
