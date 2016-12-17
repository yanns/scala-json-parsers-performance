package jsonperf

trait JsonParsing[A] extends Serializable {
  def deserialize(s: String): A
  def serialize(a: A): String
}