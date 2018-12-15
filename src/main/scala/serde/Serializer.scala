package serde

// serializer

abstract class Serializer {
  def serializeBoolean(b: Boolean): Unit
  def serializeString(s: String): Unit
  def serializeInt(i: Int): Unit
  def serializeNull(): Unit
  def serializeIterator[A](i: Iterator[A])(implicit serializeA: Serialize[A]): Unit
  def serializeMap(): MapSerializer
}

abstract class MapSerializer {
  def add[A: Serialize](key: String, a: A): Unit
  def end(): Unit
}

