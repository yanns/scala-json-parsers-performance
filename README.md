Scala JSON parser performance comparator
========================================

Compares performance of several JSON parsers used in Scala.
The test case is to deserialize a json into a case class.

The performances are measure 2 ways:
- with [ScalaMeter](http://scalameter.github.io/)
- with [JMH](https://github.com/ktoso/sbt-jmh)

# result
## with a little json:
### ScalaMeter results:

    [info] Parameters(parser -> argonaut): 0.206251
    [info] Parameters(parser -> jackson): 0.058198
    [info] Parameters(parser -> json4sJackson): 0.198448
    [info] Parameters(parser -> json4sNative): 0.231234
    [info] Parameters(parser -> playJson): 0.21752
    [info] Parameters(parser -> sphereJson): 0.117179
    [info] Parameters(parser -> sprayJson): 0.19467

### JMH results:

    [info] SmallJsonBenchmark.runArgonautJson    avgt    5   0.002 ±  0.001  ms/op
    [info] SmallJsonBenchmark.runJacksonParsing  avgt    5   0.001 ±  0.001  ms/op
    [info] SmallJsonBenchmark.runJson4sJackson   avgt    5   0.002 ±  0.001  ms/op
    [info] SmallJsonBenchmark.runJson4sNative    avgt    5   0.002 ±  0.001  ms/op
    [info] SmallJsonBenchmark.runNoParsing       avgt    5  ≈ 10⁻⁶           ms/op
    [info] SmallJsonBenchmark.runPlayJson        avgt    5   0.003 ±  0.001  ms/op
    [info] SmallJsonBenchmark.runSphereJson      avgt    5   0.001 ±  0.001  ms/op
    [info] SmallJsonBenchmark.runSprayJson       avgt    5   0.003 ±  0.001  ms/op

## with a big json:
### ScalaMeter results:

    [info] Parameters(parser -> argonaut): 6.862806
    [info] Parameters(parser -> jackson): 2.917868
    [info] Parameters(parser -> json4sJackson): 9.895644
    [info] Parameters(parser -> json4sNative): 12.452062
    [info] Parameters(parser -> playJson): 9.54849
    [info] Parameters(parser -> sphereJson): 5.895542
    [info] Parameters(parser -> sprayJson): 3.030669

### JMH results:

    [info] BigJsonBenchmark.runArgonautJson      avgt    5   1.206 ±  0.040  ms/op
    [info] BigJsonBenchmark.runJacksonParsing    avgt    5   1.696 ±  0.275  ms/op
    [info] BigJsonBenchmark.runJson4sJackson     avgt    5   2.201 ±  0.154  ms/op
    [info] BigJsonBenchmark.runJson4sNative      avgt    5   2.270 ±  0.077  ms/op
    [info] BigJsonBenchmark.runNoParsing         avgt    5   0.108 ±  0.003  ms/op
    [info] BigJsonBenchmark.runPlayJson          avgt    5   2.285 ±  0.149  ms/op
    [info] BigJsonBenchmark.runSphereJson        avgt    5   3.301 ±  0.116  ms/op
    [info] BigJsonBenchmark.runSprayJson         avgt    5   1.333 ±  0.009  ms/op

# How to test it yourself?

Simply clone this repository and run sbt:

## for ScalaMeter:

    test


## for JMH

    run

for a quick feedback:

    run -i 3 -wi 3 -f1 -t1
