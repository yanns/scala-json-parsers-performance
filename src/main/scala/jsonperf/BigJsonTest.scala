package jsonperf
import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import refuel.json.{Codec, CodecDef}
import upickle.default

case class Person(name: String, age: Int)

case class BigJson(colleagues: Vector[Person])

class BigJsonTest extends JsonTest[BigJson] with Serializable with CodecDef {

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

  override def checkResult(result: BigJson): Unit = {
    assert(result.colleagues.size == total, s"result.colleagues.size(${result.colleagues.size}) != $total")
    for (i ← 1 to 1000) {
      val c = result.colleagues(i - 1)
      assert(c.name == s"person-$i", s"name(${c.name}) != 'person-$i'")
      assert(c.age == i)
    }
  }

  override def refuelCodec: Codec[BigJson] = CaseClassCodec.from[BigJson]
}

