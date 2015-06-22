package jsonperf

import org.scalameter.{Gen, PerformanceTest}

trait PerfTest[A] extends PerformanceTest.Microbenchmark {
  val test: JsonTest[A]
  import test._

  def jsonParsings: Gen[Parsing] = Gen.enumeration("parser")(
    noParsing, argonautJson, jacksonParsing, json4sJackson, json4sNative, playJson, sphereJson, sprayJson, noParsing)

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


object SmallJsonPerf extends PerfTest[SmallJson] {
  override val test = new SmallJsonTest

  testRun()
}

object BigJsonPerf extends PerfTest[BigJson] {
  override val test = new BigJsonTest

  testRun()
}
