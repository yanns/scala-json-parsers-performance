package jsonperf

import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class UnitTest extends AnyFreeSpec with Matchers with TableDrivenPropertyChecks {

  val tests = Table(
    "test",
    new BigJsonTest
  )

  forAll(tests) { test ⇒
    s"with test ${test.getClass.getName}" - {
      import test._
      val jsonParsings = Table(
        "parser",
        argonautJson, jacksonParsing, json4sJackson, json4sNative, playJson, sprayJson, circeJson, jsoniter, uPickle, weePickle, borer)

      forAll(jsonParsings) { jsonParsing ⇒
        s"using parser '$jsonParsing'" - {
          "should deserialize json" in {
            val json = test.json
            val result = jsonParsing.deserialize(json)
            result shouldEqual test.newA
          }

          "should serialize json" in {
            val a = test.newA
            val result = jsonParsing.serialize(a)
            val aa = jsonParsing.deserialize(result)
            aa should be (a)
          }
        }
      }
    }
  }

}
