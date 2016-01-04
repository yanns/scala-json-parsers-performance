package jsonperf

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations._


@State(Scope.Benchmark)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.MILLISECONDS)
abstract class JmhBenchmarks[A](test: JsonTest[A]) {
  import test._

  def runTest(implementation: JsonParsing[A]) = {
    implementation(test.json)
  }

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

}

class SmallJsonBenchmark extends JmhBenchmarks(new SmallJsonTest)
class BigJsonBenchmark extends JmhBenchmarks(new BigJsonTest)