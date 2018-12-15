package jsonperf
import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import serde.{Serialize, Serializer}
import upickle.default

case class Person(name: String, age: Int)
case class BigJson(colleagues: Vector[Person])

class BigJsonTest extends JsonTest[BigJson] with Serializable {

  val total = 1000
  def colleagues = (for (i ← 1 to 1000) yield s"""{"name": "person-$i", "age": $i}""").mkString(", ")
  val json =
    s"""{
      |  "colleagues": [
      |    $colleagues
      |  ]
      |}
    """.stripMargin

  override val newA = BigJson(colleagues = (for (i ← 1 to 1000) yield Person(s"person-$i", i)).toVector)
  override val clazz = classOf[BigJson]

  override def playFormat = {
    import play.api.libs.json.Json
    implicit val personReads = Json.format[Person]
    Json.format[BigJson]
  }

  override def sphereJSON = {
    import io.sphere.json.generic._
    implicit val personFromJson = jsonProduct(Person.apply _)
    deriveJSON[BigJson]
  }

  override def sprayJsonFormat = {
    import spray.json.DefaultJsonProtocol._
    implicit val personFormat = spray.json.DefaultJsonProtocol.jsonFormat2(Person)
    spray.json.DefaultJsonProtocol.jsonFormat1(BigJson)
  }

  override def argonautCodec = {
    import argonaut.Argonaut._
    implicit val personCode = casecodec2(Person.apply, Person.unapply)("name", "age")
    casecodec1(BigJson.apply, BigJson.unapply)("colleagues")
  }

  override def circeEncoder = {
    import io.circe.generic.semiauto._
    implicit val personEncoder = deriveEncoder[Person]
    deriveEncoder[BigJson]
  }

  override def circeDecoder = {
    import io.circe.generic.semiauto._
    implicit val personDecoder = deriveDecoder[Person]
    deriveDecoder[BigJson]
  }


  override def jsoniterCodec: JsonValueCodec[BigJson] = {
    import com.github.plokhotnyuk.jsoniter_scala.macros._
    JsonCodecMaker.make[BigJson](CodecMakerConfig())
  }


  override def uJsonRW: default.ReadWriter[BigJson] = {
    import upickle.default.{ReadWriter => RW, macroRW}
    implicit val personRW: RW[Person] = macroRW
    upickle.default.macroRW
  }

  override def serdeSerialize: Serialize[BigJson] = new Serialize[BigJson]{
    import serde.DefaultSerialize._
    implicit val personSerialize: Serialize[Person] = new Serialize[Person] {
      override def serialize(a: Person, serializer: Serializer): Unit = {
        val map = serializer.serializeMap()
        map.add("name", a.name)
        map.add("age", a.age)
        map.end()
      }
    }
    override def serialize(a: BigJson, serializer: Serializer): Unit = {
      val map = serializer.serializeMap()
      map.add("colleagues", a.colleagues)
      map.end()
    }
  }

  override def checkResult(result: BigJson): Unit = {
    assert(result.colleagues.size == total, s"result.colleagues.size(${result.colleagues.size}) != $total")
    for (i ← 1 to 1000) {
      val c = result.colleagues(i - 1)
      assert(c.name == s"person-$i", s"name(${c.name}) != 'person-$i'")
      assert(c.age == i)
    }
  }
}

