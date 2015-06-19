package jsonperf


case class SmallJson(stringField: String, intNumber: Int, floatingNumber: Double, boolean: Boolean)

class SmallJsonTest extends JsonTest[SmallJson] with Serializable {

  val json =
    """{
      |  "stringField": "hello",
      |  "intNumber": 5639,
      |  "floatingNumber": 345.23,
      |  "boolean": true
      |}
    """.stripMargin

  override val newA = SmallJson("hello", 5639, 345.23, true)
  override def playRead = play.api.libs.json.Json.reads[SmallJson]
  override def sphereFromJson = io.sphere.json.generic.jsonProduct((SmallJson.apply _).curried)
  override val clazz = classOf[SmallJson]

  override def checkResult(result: SmallJson): Unit = {
    assert(result.stringField == "hello")
    assert(result.intNumber == 5639)
    assert(result.floatingNumber == 345.23)
    assert(result.boolean)
  }
}

object SmallJsonPerf extends PerfTest[SmallJson] {
  override val test = new SmallJsonTest

  testRun()

}
