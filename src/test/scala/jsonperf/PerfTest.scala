package jsonperf

import org.scalameter.{Gen, PerformanceTest}

trait PerfTest[A] extends PerformanceTest.Microbenchmark {
  val test: JsonTest[A]
  import test._

  def jsonParsings: Gen[Parsing] = Gen.enumeration("parser")(noParsing, jacksonParsing, json4sNative, json4sJackson, sphereJson, playJson, sprayJson, argonautJson)

  def testRun() =
    performance of "JsonParser" in {
      measure method "deserialize" in {
        using(jsonParsings) in { jsonParsing ⇒
          val result = jsonParsing(json)
          test.checkResult(result)
        }
      }
    }

}
