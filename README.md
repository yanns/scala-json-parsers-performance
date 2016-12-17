Scala JSON parser performance comparator
========================================

Compares performance of several JSON parsers used in Scala.
The test case is to deserialize a json into a case class.

The performances are measured with [JMH](https://github.com/ktoso/sbt-jmh)

# result

## with a big json:

### JMH results:

    [info] BigJsonBenchmark.runArgonautJson      avgt   10   1.039 ±  0.023  ms/op
    [info] BigJsonBenchmark.runJacksonParsing    avgt   10   0.211 ±  0.004  ms/op
    [info] BigJsonBenchmark.runJson4sJackson     avgt   10   1.287 ±  0.028  ms/op
    [info] BigJsonBenchmark.runJson4sNative      avgt   10   1.332 ±  0.015  ms/op
    [info] BigJsonBenchmark.runPlayJson          avgt   10   1.321 ±  0.014  ms/op
    [info] BigJsonBenchmark.runSphereJson        avgt   10   0.809 ±  0.011  ms/op
    [info] BigJsonBenchmark.runSprayJson         avgt   10   0.356 ±  0.005  ms/op

# How to test it yourself?

Simply clone this repository and run sbt:

## for JMH

    jmh:run -i 10 -wi 10 -f1 -t1

for a quick feedback:

    jmh:run -i 3 -wi 3 -f1 -t1

## to check how many objects are allocated

    jmh:run -i 10 -wi 10 -f1 -t1 -prof gc
