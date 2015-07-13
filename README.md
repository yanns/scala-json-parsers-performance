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

    [info] Parameters(parser -> argonaut): 0.204488
    [info] Parameters(parser -> jackson): 0.058759
    [info] Parameters(parser -> json4sJackson): 0.211516
    [info] Parameters(parser -> json4sNative): 0.259956
    [info] Parameters(parser -> playJson): 0.221491
    [info] Parameters(parser -> sphereJson): 0.118997
    [info] Parameters(parser -> sprayJson): 0.195646

### JMH results:

    [info] SmallJsonBenchmark.runArgonautJson    avgt    3   0.002 ±  0.003  ms/op
    [info] SmallJsonBenchmark.runJacksonParsing  avgt    3   0.001 ±  0.001  ms/op
    [info] SmallJsonBenchmark.runJson4sJackson   avgt    3   0.002 ±  0.001  ms/op
    [info] SmallJsonBenchmark.runJson4sNative    avgt    3   0.002 ±  0.001  ms/op
    [info] SmallJsonBenchmark.runNoParsing       avgt    3  ≈ 10⁻⁶           ms/op
    [info] SmallJsonBenchmark.runPlayJson        avgt    3   0.003 ±  0.001  ms/op
    [info] SmallJsonBenchmark.runSphereJson      avgt    3   0.001 ±  0.002  ms/op
    [info] SmallJsonBenchmark.runSprayJson       avgt    3   0.003 ±  0.003  ms/op

## with a big json:
### ScalaMeter results:

    [info] Parameters(parser -> argonaut): 7.429903
    [info] Parameters(parser -> jackson): 2.887989
    [info] Parameters(parser -> json4sJackson): 10.013866
    [info] Parameters(parser -> json4sNative): 11.571219
    [info] Parameters(parser -> playJson): 10.316594
    [info] Parameters(parser -> sphereJson): 6.079708
    [info] Parameters(parser -> sprayJson): 2.822319
    [info] Parameters(parser -> sprayJson): 3.030669

### JMH results:

    [info] BigJsonBenchmark.runArgonautJson      avgt    3   1.208 ±  0.699  ms/op
    [info] BigJsonBenchmark.runJacksonParsing    avgt    3   1.930 ±  4.286  ms/op
    [info] BigJsonBenchmark.runJson4sJackson     avgt    3   2.445 ±  0.473  ms/op
    [info] BigJsonBenchmark.runJson4sNative      avgt    3   2.395 ±  1.488  ms/op
    [info] BigJsonBenchmark.runNoParsing         avgt    3   0.116 ±  0.113  ms/op
    [info] BigJsonBenchmark.runPlayJson          avgt    3   2.362 ±  1.001  ms/op
    [info] BigJsonBenchmark.runSphereJson        avgt    3   3.505 ±  1.063  ms/op
    [info] BigJsonBenchmark.runSprayJson         avgt    3   1.363 ±  0.459  ms/op

# How to test it yourself?

Simply clone this repository and run sbt:

## for ScalaMeter:

    test


## for JMH

    run

for a quick feedback:

    run -i 3 -wi 3 -f1 -t1
