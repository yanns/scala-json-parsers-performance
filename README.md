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

    [info] Parameters(parser -> argonaut): 0.194616
    [info] Parameters(parser -> jackson): 0.058777
    [info] Parameters(parser -> json4sJackson): 0.224313
    [info] Parameters(parser -> json4sNative): 0.23916
    [info] Parameters(parser -> playJson): 0.245464
    [info] Parameters(parser -> sphereJson): 0.143125
    [info] Parameters(parser -> sprayJson): 0.198715

### JMH results:

    [info] SmallJsonBenchmark.runArgonautJson    avgt   10   0.002 ±  0.001  ms/op
    [info] SmallJsonBenchmark.runJacksonParsing  avgt   10   0.001 ±  0.001  ms/op
    [info] SmallJsonBenchmark.runJson4sJackson   avgt   10   0.002 ±  0.001  ms/op
    [info] SmallJsonBenchmark.runJson4sNative    avgt   10   0.002 ±  0.001  ms/op
    [info] SmallJsonBenchmark.runPlayJson        avgt   10   0.003 ±  0.001  ms/op
    [info] SmallJsonBenchmark.runSphereJson      avgt   10   0.001 ±  0.001  ms/op
    [info] SmallJsonBenchmark.runSprayJson       avgt   10   0.003 ±  0.001  ms/op

## with a big json:
### ScalaMeter results:

    [info] Parameters(parser -> argonaut): 7.963992
    [info] Parameters(parser -> jackson): 2.28583
    [info] Parameters(parser -> json4sJackson): 8.755903
    [info] Parameters(parser -> json4sNative): 9.574484
    [info] Parameters(parser -> playJson): 7.532002
    [info] Parameters(parser -> sphereJson): 2.751175
    [info] Parameters(parser -> sprayJson): 1.505254

### JMH results:

    [info] BigJsonBenchmark.runArgonautJson      avgt   10   1.068 ±  0.014  ms/op
    [info] BigJsonBenchmark.runJacksonParsing    avgt   10   0.200 ±  0.003  ms/op
    [info] BigJsonBenchmark.runJson4sJackson     avgt   10   1.302 ±  0.015  ms/op
    [info] BigJsonBenchmark.runJson4sNative      avgt   10   1.350 ±  0.014  ms/op
    [info] BigJsonBenchmark.runPlayJson          avgt   10   1.304 ±  0.017  ms/op
    [info] BigJsonBenchmark.runSphereJson        avgt   10   0.778 ±  0.015  ms/op
    [info] BigJsonBenchmark.runSprayJson         avgt   10   0.359 ±  0.008  ms/op

# How to test it yourself?

Simply clone this repository and run sbt:

## for ScalaMeter:

    test


## for JMH

    jmh:run -i 10 -wi 10 -f1 -t1

for a quick feedback:

    jmh:run -i 3 -wi 3 -f1 -t1
