Scala JSON parser performance comparator
========================================

Compares performance of several JSON parsers used in Scala.
The test case is to deserialize a json into a case class.

The performances are measured in 2 ways:
- with [ScalaMeter](http://scalameter.github.io/)
- with [JMH](https://github.com/ktoso/sbt-jmh)

# result
## with a little json:
### ScalaMeter results:

    [info] Parameters(parser -> argonaut): 0.212092
    [info] Parameters(parser -> jackson): 0.063209
    [info] Parameters(parser -> json4sJackson): 0.222026
    [info] Parameters(parser -> json4sNative): 0.241549
    [info] Parameters(parser -> playJson): 0.239287
    [info] Parameters(parser -> sphereJson): 0.143652
    [info] Parameters(parser -> sprayJson): 0.196486

### JMH results:

    [info] SmallJsonBenchmark.runArgonautJson    avgt   10   0.002 ±  0.001  ms/op
    [info] SmallJsonBenchmark.runJacksonParsing  avgt   10   0.001 ±  0.001  ms/op
    [info] SmallJsonBenchmark.runJson4sJackson   avgt   10   0.002 ±  0.001  ms/op
    [info] SmallJsonBenchmark.runJson4sNative    avgt   10   0.003 ±  0.001  ms/op
    [info] SmallJsonBenchmark.runNoParsing       avgt   10  ≈ 10⁻⁶           ms/op
    [info] SmallJsonBenchmark.runPlayJson        avgt   10   0.003 ±  0.001  ms/op
    [info] SmallJsonBenchmark.runSphereJson      avgt   10   0.002 ±  0.001  ms/op
    [info] SmallJsonBenchmark.runSprayJson       avgt   10   0.003 ±  0.001  ms/op

## with a big json:
### ScalaMeter results:

    [info] Parameters(parser -> argonaut): 8.677875
    [info] Parameters(parser -> jackson): 3.086129
    [info] Parameters(parser -> json4sJackson): 10.153181
    [info] Parameters(parser -> json4sNative): 12.479775
    [info] Parameters(parser -> playJson): 12.643583
    [info] Parameters(parser -> sphereJson): 6.353633
    [info] Parameters(parser -> sprayJson): 3.18222

### JMH results:

    [info] BigJsonBenchmark.runArgonautJson      avgt   10   1.238 ±  0.019  ms/op
    [info] BigJsonBenchmark.runJacksonParsing    avgt   10   1.839 ±  0.105  ms/op
    [info] BigJsonBenchmark.runJson4sJackson     avgt   10   2.267 ±  0.028  ms/op
    [info] BigJsonBenchmark.runJson4sNative      avgt   10   2.611 ±  0.226  ms/op
    [info] BigJsonBenchmark.runNoParsing         avgt   10   0.122 ±  0.020  ms/op
    [info] BigJsonBenchmark.runPlayJson          avgt   10   2.566 ±  0.293  ms/op
    [info] BigJsonBenchmark.runSphereJson        avgt   10   3.623 ±  0.286  ms/op
    [info] BigJsonBenchmark.runSprayJson         avgt   10   1.424 ±  0.042  ms/op

# How to test it yourself?

Simply clone this repository and run sbt:

## for ScalaMeter:

    test


## for JMH

    jmh:run

for a quick feedback:

    jmh:run -i 3 -wi 3 -f1 -t1
