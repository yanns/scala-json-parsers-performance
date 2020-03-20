package jsonperf

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations._


@State(Scope.Benchmark)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.MILLISECONDS)
abstract class JmhBenchmarks[A <: AnyRef](val test: JsonTest[A]) {
  import test._

  def runTest(implementation: JsonParsing[A]): Unit

  @Benchmark
  def runRefuelParsing() = runTest(refuelParsing)

  @Benchmark
  def runNoParsing() = runTest(noParsing)

  @Benchmark
  def runJacksonParsing() = runTest(jacksonParsing)

  @Benchmark
  def runJson4sNative() = runTest(json4sNative)

  @Benchmark
  def runJson4sJackson() = runTest(json4sJackson)

  @Benchmark
  def runSphereJson() = runTest(sphereJson)

  @Benchmark
  def runPlayJson() = runTest(playJson)

  @Benchmark
  def runSprayJson() = runTest(sprayJson)

  @Benchmark
  def runArgonautJson() = runTest(argonautJson)

  @Benchmark
  def runCirce() = runTest(circeJson)

  @Benchmark
  def runJsoniter() = runTest(jsoniter)

  @Benchmark
  def runUJson() = runTest(uJson)
}

class BigJsonBenchmarkDeserialize extends JmhBenchmarks(new BigJsonTest) {
  def runTest(implementation: JsonParsing[BigJson]): Unit = {
    implementation.deserialize(test.json)
  }
}

class BigJsonBenchmarkSerialize extends JmhBenchmarks(new BigJsonTest) {
  def runTest(implementation: JsonParsing[BigJson]): Unit = {
    implementation.serialize(test.newA)
  }
}
