package serdetest

import serde._
import serde.DefaultSerialize._

case class User(name: String, age: Int, parents: Option[Vector[User]])

object User {
  implicit val serialize: Serialize[User] = new Serialize[User] {
    override def serialize(a: User, serializer: Serializer): Unit = {
      val map = serializer.serializeMap()
      map.add("name", a.name)
      map.add("age", a.age)
      map.add("parents", a.parents)
      map.end()
    }
  }
}

object Main { //extends App {
  val yann = User("yann", 40, None)
  val gwen = User("gwen", 40, None)
  val quentin = User("quentin", 12, Some(Vector(yann, gwen)))
  println(JsonSerializer.serialize(yann).utf8String)
  println(JsonSerializer.serialize(quentin).utf8String)
}
