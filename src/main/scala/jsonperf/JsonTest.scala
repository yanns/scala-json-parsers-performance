package jsonperf

abstract class JsonTest[A <: AnyRef](implicit ev: scala.reflect.Manifest[A]) extends Serializable {

  def json: String
  def newA: A
  def clazz: Class[A]
  def sphereJSON: io.sphere.json.JSON[A]
  def playFormat: play.api.libs.json.Format[A]
  def sprayJsonFormat: spray.json.JsonFormat[A]
  def argonautCodec: argonaut.CodecJson[A]
  def checkResult(result: A): Unit


  type Parsing = JsonParsing[A]

  val noParsing: Parsing = new Parsing {
    override def deserialize(json: String): A = newA
    override def serialize(a: A): String = ""
    override def toString(): String = "noParsing"
  }
  val jacksonParsing: Parsing = new Parsing {
    override def deserialize(json: String): A = Jackson.mapper.readValue(json, clazz)
    override def serialize(a: A): String = Jackson.mapper.writeValueAsString(a)
    override def toString(): String = "jackson"
  }

  val json4sNative: Parsing = new Parsing {
    override def deserialize(json: String): A = {
      import org.json4s._
      import org.json4s.native.JsonMethods._
      implicit val formats = DefaultFormats
      parse(json).extract[A]
    }
    override def serialize(a: A): String = {
      import org.json4s._
      import org.json4s.native.Serialization
      implicit val formats = Serialization.formats(NoTypeHints)
      Serialization.write(a)
    }
    override def toString(): String = "json4sNative"
  }

  val json4sJackson: Parsing = new Parsing {
    override def deserialize(json: String): A = {
      import org.json4s._
      import org.json4s.jackson.JsonMethods._
      implicit val formats = DefaultFormats
      parse(json).extract[A]
    }
    override def serialize(a: A): String = {
      import org.json4s._
      import org.json4s.jackson.Serialization
      implicit val formats = Serialization.formats(NoTypeHints)
      Serialization.write(a)
    }
    override def toString(): String = "json4sJackson"
  }

  val sphereJson: Parsing = new Parsing {
    override def deserialize(json: String): A = {
      io.sphere.json.getFromJSON(json)(sphereJSON)
    }
    override def serialize(a: A): String = {
      io.sphere.json.toJSON(a)(sphereJSON)
    }
    override def toString(): String = "sphereJson"
  }

  val playJson: Parsing = new Parsing {
    override def deserialize(json: String): A = {
      import play.api.libs.json.Json
      Json.parse(json).as[A](playFormat)
    }
    override def serialize(a: A): String = {
      import play.api.libs.json.Json
      Json.stringify(Json.toJson(a)(playFormat))
    }
    override def toString(): String = "playJson"
  }

  val sprayJson: Parsing = new Parsing {
    override def deserialize(json: String): A = {
      spray.json.JsonParser(json).convertTo[A](sprayJsonFormat)
    }
    override def serialize(a: A): String = {
      import spray.json._
      a.toJson(sprayJsonFormat).compactPrint
    }
    override def toString(): String = "sprayJson"
  }

  val argonautJson : Parsing = new Parsing {
    override def deserialize(json: String): A = {
      import argonaut.Argonaut._
      json.decodeOption[A](argonautCodec).get
    }
    override def serialize(a: A): String = {
      import argonaut.Argonaut._
      a.asJson(argonautCodec).toString()
    }

    override def toString(): String = "argonaut"
  }
}
