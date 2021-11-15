package jsonperf

import java.nio.charset.StandardCharsets

abstract class JsonTest[A <: AnyRef](implicit ev: scala.reflect.Manifest[A]) extends Serializable {

  def json: String
  def newA: A
  def clazz: Class[A]
  def refuelCodec: refuel.json.codecs.Codec[A]
  def sphereJSON: io.sphere.json.JSON[A]
  def playFormat: play.api.libs.json.Format[A]
  def sprayJsonFormat: spray.json.JsonFormat[A]
  def argonautCodec: argonaut.CodecJson[A]
  def circeEncoder: io.circe.Encoder[A]
  def circeDecoder: io.circe.Decoder[A]
  def jsoniterCodec: com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec[A]
  def uPickleRW: upickle.default.ReadWriter[A]
  def weePickleFromTo: com.rallyhealth.weepickle.v1.WeePickle.FromTo[A]
  def borerCodec: io.bullet.borer.Codec[A]

  def checkResult(result: A): Unit


  type Parsing = JsonParsing[A]

  val refuelParsing = new Parsing with refuel.json.JsonTransform {
    val codec: refuel.json.codecs.Codec[A] = refuelCodec
    override def deserialize(json: String): A = json.readAs[A](codec).get
    override def serialize(a: A): String = a.writeAsString(codec).get
    override def toString: String = "refuelParsing"
  }

  val noParsing: Parsing = new Parsing {
    override def deserialize(json: String): A = newA
    override def serialize(a: A): String = ""
    override def toString: String = "noParsing"
  }

  val jacksonParsing: Parsing = new Parsing {
    override def deserialize(json: String): A = Jackson.mapper.readValue(json, clazz)
    override def serialize(a: A): String = Jackson.mapper.writeValueAsString(a)
    override def toString: String = "jackson"
  }

  val json4sNative: Parsing = new Parsing {
    import org.json4s._
    import org.json4s.native.JsonMethods._
    import org.json4s.native.Serialization
    implicit val formats = DefaultFormats
    override def deserialize(json: String): A = {
      org.json4s.native.JsonMethods.parse(json).extract[A]
    }
    override def serialize(a: A): String = {
      Serialization.write(a)
    }
    override def toString: String = "json4sNative"
  }

  val json4sJackson: Parsing = new Parsing {
    import org.json4s._
    import org.json4s.jackson.JsonMethods._
    import org.json4s.jackson.Serialization
    implicit val formats = DefaultFormats
    override def deserialize(json: String): A = {
      parse(json).extract[A]
    }
    override def serialize(a: A): String = {
      Serialization.write(a)
    }
    override def toString: String = "json4sJackson"
  }

  val sphereJson: Parsing = new Parsing {
    val fromToJson = sphereJSON
    override def deserialize(json: String): A = {
      io.sphere.json.getFromJSON(json)(fromToJson)
    }
    override def serialize(a: A): String = {
      io.sphere.json.toJSON(a)(fromToJson)
    }
    override def toString: String = "sphereJson"
  }

  val playJson: Parsing = new Parsing {
    val format = playFormat
    override def deserialize(json: String): A = {
      import play.api.libs.json.Json
      Json.parse(json).as[A](format)
    }
    override def serialize(a: A): String = {
      import play.api.libs.json.Json
      Json.stringify(Json.toJson(a)(format))
    }
    override def toString: String = "playJson"
  }

  val sprayJson: Parsing = new Parsing {
    val format = sprayJsonFormat
    override def deserialize(json: String): A = {
      spray.json.JsonParser(json).convertTo[A](format)
    }
    override def serialize(a: A): String = {
      import spray.json._
      a.toJson(format).compactPrint
    }
    override def toString: String = "sprayJson"
  }

  val argonautJson: Parsing = new Parsing {
    val codec = argonautCodec
    override def deserialize(json: String): A = {
      import argonaut.Argonaut._
      json.decodeOption[A](codec).get
    }
    override def serialize(a: A): String = {
      import argonaut.Argonaut._
      a.asJson(codec).toString()
    }
    override def toString: String = "argonaut"
  }

  val circeJson: Parsing = new Parsing {
    val encoder = circeEncoder
    val decoder = circeDecoder
    override def deserialize(json: String): A = {
      io.circe.parser.decode[A](json)(decoder).getOrElse(throw new Exception)
    }
    override def serialize(a: A): String = {
      import io.circe.syntax._
      a.asJson(encoder).noSpaces
    }
    override def toString: String = "circe"
  }

  val jsoniter: Parsing = new Parsing {
    import com.github.plokhotnyuk.jsoniter_scala.core._
    val codec = jsoniterCodec
    override def deserialize(s: String): A = {
      readFromString(s)(codec)
    }
    override def serialize(a: A): String = {
      writeToString(a)(codec)
    }
    override def toString: String = "jsoniter"
  }

  val uPickle: Parsing = new Parsing {
    override def deserialize(s: String): A = {
      upickle.default.read[A](s)(uPickleRW)
    }
    override def serialize(a: A): String = {
      upickle.default.write[A](a)(uPickleRW)
    }
    override def toString: String = "uPickle"
  }

  val weePickle: Parsing = new Parsing {
    import com.rallyhealth.weejson.v1.jackson._
    import com.rallyhealth.weepickle.v1.WeePickle._
    val fromTo = weePickleFromTo
    override def deserialize(s: String): A = {
      FromJson(s).transform(ToScala[A](fromTo))
    }
    override def serialize(a: A): String = {
      FromScala(a)(fromTo).transform(ToJson.string)
    }
    override def toString: String = "weePickle"
  }

  val borer: Parsing = new Parsing {
    import io.bullet.borer.{Codec, Decoder, Encoder, Json}
    implicit val Codec(encoder: Encoder[A], decoder: Decoder[A]) = borerCodec
    override def deserialize(s: String): A = {
      Json.decode(s.getBytes(StandardCharsets.UTF_8)).to[A].value
    }
    override def serialize(a: A): String = {
      Json.encode(a).toUtf8String
    }
    override def toString: String = "borer"
  }
}
