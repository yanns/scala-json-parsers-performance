package jsonperf

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations._


@State(Scope.Benchmark)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1, jvmArgs = Array(
  "-server",
  "-Xms1g",
  "-Xmx1g",
  "-XX:NewSize=512m",
  "-XX:MaxNewSize=512m",
  "-XX:InitialCodeCacheSize=256m",
  "-XX:ReservedCodeCacheSize=256m",
  "-XX:+UseParallelGC",
  "-XX:-UseAdaptiveSizePolicy",
  "-XX:MaxInlineLevel=18",
  "-XX:-UseBiasedLocking",
  "-XX:+AlwaysPreTouch",
  "-XX:+UseNUMA",
  "-XX:-UseAdaptiveNUMAChunkSizing"
))
abstract class JmhBenchmarks[A <: AnyRef, B](val test: JsonTest[A]) {
  import test._

  def runTest(implementation: JsonParsing[A]): B

  @Benchmark
  def runRefuelParsing: B = runTest(refuelParsing)

  @Benchmark
  def runNoParsing: B = runTest(noParsing)

  @Benchmark
  def runJacksonParsing: B = runTest(jacksonParsing)

  @Benchmark
  def runJson4sNative: B = runTest(json4sNative)

  @Benchmark
  def runJson4sJackson: B = runTest(json4sJackson)

  @Benchmark
  def runSphereJson: B = runTest(sphereJson)

  @Benchmark
  def runPlayJson: B = runTest(playJson)

  @Benchmark
  def runSprayJson: B = runTest(sprayJson)

  @Benchmark
  def runArgonautJson: B = runTest(argonautJson)

  @Benchmark
  def runCirce: B = runTest(circeJson)

  @Benchmark
  def runJsoniter: B = runTest(jsoniter)

  @Benchmark
  def runUPickle: B = runTest(uPickle)

  @Benchmark
  def runWeePickle: B = runTest(weePickle)

  @Benchmark
  def runBorer: B = runTest(borer)
}

class BigJsonBenchmarkDeserialize extends JmhBenchmarks[BigJson, BigJson](new BigJsonTest) {
  def runTest(implementation: JsonParsing[BigJson]): BigJson = {
    implementation.deserialize(test.json)
  }
}

class BigJsonBenchmarkSerialize extends JmhBenchmarks[BigJson, String](new BigJsonTest) {
  def runTest(implementation: JsonParsing[BigJson]): String = {
    implementation.serialize(test.newA)
  }
}
