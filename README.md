Scala JSON parser performance comparator
========================================

Compares performance of several JSON parsers used in Scala.
The test case is to deserialize a json into a case class.

The performances are measured with [JMH](https://github.com/ktoso/sbt-jmh)

# Performance test

## Run with

    jmh:run -i 10 -wi 10 -f1 -t1

(for a quick feedback:)

    jmh:run -i 3 -wi 3 -f1 -t1


## JMH results

    [info] BigJsonBenchmark.runArgonautJson      avgt   10   1.039 ±  0.023  ms/op
    [info] BigJsonBenchmark.runJacksonParsing    avgt   10   0.211 ±  0.004  ms/op
    [info] BigJsonBenchmark.runJson4sJackson     avgt   10   1.287 ±  0.028  ms/op
    [info] BigJsonBenchmark.runJson4sNative      avgt   10   1.332 ±  0.015  ms/op
    [info] BigJsonBenchmark.runPlayJson          avgt   10   1.321 ±  0.014  ms/op
    [info] BigJsonBenchmark.runSphereJson        avgt   10   0.809 ±  0.011  ms/op
    [info] BigJsonBenchmark.runSprayJson         avgt   10   0.356 ±  0.005  ms/op

# Pressure on the GC

## Run with

    jmh:run -i 10 -wi 10 -f1 -t1 -prof gc

## Results

```
[info] Benchmark                                                            Mode  Cnt        Score        Error   Units
[info] BigJsonBenchmark.runArgonautJson:·gc.alloc.rate.norm                 avgt   10  3484610.578 ±      7.003    B/op
[info] BigJsonBenchmark.runArgonautJson:·gc.count                           avgt   10      106.000               counts
[info] BigJsonBenchmark.runArgonautJson:·gc.time                            avgt   10       92.000                   ms

[info] BigJsonBenchmark.runJacksonParsing:·gc.alloc.rate.norm               avgt   10   150640.337 ±      1.181    B/op
[info] BigJsonBenchmark.runJacksonParsing:·gc.count                         avgt   10       65.000               counts
[info] BigJsonBenchmark.runJacksonParsing:·gc.time                          avgt   10       40.000                   ms

[info] BigJsonBenchmark.runJson4sJackson:·gc.alloc.rate.norm                avgt   10  1964666.089 ±      7.235    B/op
[info] BigJsonBenchmark.runJson4sJackson:·gc.count                          avgt   10      130.000               counts
[info] BigJsonBenchmark.runJson4sJackson:·gc.time                           avgt   10       99.000                   ms

[info] BigJsonBenchmark.runJson4sNative:·gc.alloc.rate.norm                 avgt   10  2516786.161 ±      7.465    B/op
[info] BigJsonBenchmark.runJson4sNative:·gc.count                           avgt   10      118.000               counts
[info] BigJsonBenchmark.runJson4sNative:·gc.time                            avgt   10       97.000                   ms

[info] BigJsonBenchmark.runPlayJson:·gc.alloc.rate.norm                     avgt   10  3155242.163 ±      7.595    B/op
[info] BigJsonBenchmark.runPlayJson:·gc.count                               avgt   10      126.000               counts
[info] BigJsonBenchmark.runPlayJson:·gc.time                                avgt   10       97.000                   ms

[info] BigJsonBenchmark.runSphereJson:·gc.alloc.rate.norm                   avgt   10  1985121.344 ±      4.727    B/op
[info] BigJsonBenchmark.runSphereJson:·gc.count                             avgt   10      115.000               counts
[info] BigJsonBenchmark.runSphereJson:·gc.time                              avgt   10       94.000                   ms

[info] BigJsonBenchmark.runSprayJson:·gc.alloc.rate.norm                    avgt   10   494808.592 ±      2.095    B/op
[info] BigJsonBenchmark.runSprayJson:·gc.count                              avgt   10      196.000               counts
[info] BigJsonBenchmark.runSprayJson:·gc.time                               avgt   10      102.000                   ms
```