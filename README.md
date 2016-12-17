Scala JSON parser performance comparator
========================================

Compares performance of several JSON parsers used in Scala.
The test case is to deserialize a json into a case class.

The performances are measured with [JMH](https://github.com/ktoso/sbt-jmh)

# Performance test

## Run with

    jmh:run -i 10 -wi 10 -f1 -t1

(for a quick feedback:)

    jmh:run -i 2 -wi 2 -f1 -t1


## Deserialization (String -> case class)

```
[info] Benchmark                                      Mode  Cnt   Score    Error  Units
[info] BigJsonBenchmarkDeserialize.runArgonautJson    avgt   10   1.070 ±  0.018  ms/op
[info] BigJsonBenchmarkDeserialize.runJacksonParsing  avgt   10   0.209 ±  0.003  ms/op
[info] BigJsonBenchmarkDeserialize.runJson4sJackson   avgt   10   1.295 ±  0.023  ms/op
[info] BigJsonBenchmarkDeserialize.runJson4sNative    avgt   10   1.426 ±  0.380  ms/op
[info] BigJsonBenchmarkDeserialize.runPlayJson        avgt   10   1.319 ±  0.023  ms/op
[info] BigJsonBenchmarkDeserialize.runSphereJson      avgt   10   0.803 ±  0.014  ms/op
[info] BigJsonBenchmarkDeserialize.runSprayJson       avgt   10   0.358 ±  0.005  ms/op
```

## Serialization (case class -> String)

```
[info] Benchmark                                      Mode  Cnt   Score    Error  Units
[info] BigJsonBenchmarkSerialize.runArgonautJson      avgt   10   0.718 ±  0.015  ms/op
[info] BigJsonBenchmarkSerialize.runJacksonParsing    avgt   10   0.101 ±  0.002  ms/op
[info] BigJsonBenchmarkSerialize.runJson4sJackson     avgt   10   1.086 ±  0.201  ms/op
[info] BigJsonBenchmarkSerialize.runJson4sNative      avgt   10   0.900 ±  0.015  ms/op
[info] BigJsonBenchmarkSerialize.runPlayJson          avgt   10   2.137 ±  0.035  ms/op
[info] BigJsonBenchmarkSerialize.runSphereJson        avgt   10   0.361 ±  0.005  ms/op
[info] BigJsonBenchmarkSerialize.runSprayJson         avgt   10   0.427 ±  0.007  ms/op
```

# Pressure on the GC

## Run with

    jmh:run -i 10 -wi 10 -f1 -t1 -prof gc

## Deserialization (String -> case class)

```
[info] Benchmark                                                                       Mode  Cnt        Score        Error   Units
[info] BigJsonBenchmarkDeserialize.runArgonautJson:·gc.alloc.rate.norm                 avgt   10  3516725.589 ±     55.930    B/op
[info] BigJsonBenchmarkDeserialize.runArgonautJson:·gc.count                           avgt   10      127.000               counts
[info] BigJsonBenchmarkDeserialize.runArgonautJson:·gc.time                            avgt   10       98.000                   ms

[info] BigJsonBenchmarkDeserialize.runJacksonParsing:·gc.alloc.rate.norm               avgt   10   150640.377 ±      1.215    B/op
[info] BigJsonBenchmarkDeserialize.runJacksonParsing:·gc.count                         avgt   10      155.000               counts
[info] BigJsonBenchmarkDeserialize.runJacksonParsing:·gc.time                          avgt   10       91.000                   ms

[info] BigJsonBenchmarkDeserialize.runJson4sJackson:·gc.alloc.rate.norm                avgt   10  2036650.105 ±      7.364    B/op
[info] BigJsonBenchmarkDeserialize.runJson4sJackson:·gc.count                          avgt   10      119.000               counts
[info] BigJsonBenchmarkDeserialize.runJson4sJackson:·gc.time                           avgt   10       90.000                   ms

[info] BigJsonBenchmarkDeserialize.runJson4sNative:·gc.alloc.rate.norm                 avgt   10  2460778.145 ±      7.439    B/op
[info] BigJsonBenchmarkDeserialize.runJson4sNative:·gc.count                           avgt   10      128.000               counts
[info] BigJsonBenchmarkDeserialize.runJson4sNative:·gc.time                            avgt   10       95.000                   ms

[info] BigJsonBenchmarkDeserialize.runPlayJson:·gc.alloc.rate.norm                     avgt   10  3155721.594 ±     23.834    B/op
[info] BigJsonBenchmarkDeserialize.runPlayJson:·gc.count                               avgt   10      127.000               counts
[info] BigJsonBenchmarkDeserialize.runPlayJson:·gc.time                                avgt   10      100.000                   ms

[info] BigJsonBenchmarkDeserialize.runSphereJson:·gc.alloc.rate.norm                   avgt   10  1985121.334 ±      4.712    B/op
[info] BigJsonBenchmarkDeserialize.runSphereJson:·gc.count                             avgt   10      119.000               counts
[info] BigJsonBenchmarkDeserialize.runSphereJson:·gc.time                              avgt   10       97.000                   ms

[info] BigJsonBenchmarkDeserialize.runSprayJson:·gc.alloc.rate.norm                    avgt   10   494904.586 ±      2.061    B/op
[info] BigJsonBenchmarkDeserialize.runSprayJson:·gc.count                              avgt   10      194.000               counts
[info] BigJsonBenchmarkDeserialize.runSprayJson:·gc.time                               avgt   10      101.000                   ms
```

## Serialization (case class -> String):

```
[info] Benchmark                                                                       Mode  Cnt        Score        Error   Units
[info] BigJsonBenchmarkSerialize.runArgonautJson:·gc.alloc.rate.norm                   avgt   10  1695785.188 ±      4.171    B/op
[info] BigJsonBenchmarkSerialize.runArgonautJson:·gc.count                             avgt   10      141.000               counts
[info] BigJsonBenchmarkSerialize.runArgonautJson:·gc.time                              avgt   10       97.000                   ms

[info] BigJsonBenchmarkSerialize.runJacksonParsing:·gc.alloc.rate.norm                 avgt   10   112240.162 ±      0.556    B/op
[info] BigJsonBenchmarkSerialize.runJacksonParsing:·gc.count                           avgt   10      153.000               counts
[info] BigJsonBenchmarkSerialize.runJacksonParsing:·gc.time                            avgt   10       85.000                   ms

[info] BigJsonBenchmarkSerialize.runJson4sJackson:·gc.alloc.rate.norm                  avgt   10  1297273.774 ±      6.249    B/op
[info] BigJsonBenchmarkSerialize.runJson4sJackson:·gc.count                            avgt   10      142.000               counts
[info] BigJsonBenchmarkSerialize.runJson4sJackson:·gc.time                             avgt   10       91.000                   ms

[info] BigJsonBenchmarkSerialize.runJson4sNative:·gc.alloc.rate.norm                   avgt   10  1637497.493 ±      5.251    B/op
[info] BigJsonBenchmarkSerialize.runJson4sNative:·gc.count                             avgt   10      187.000               counts
[info] BigJsonBenchmarkSerialize.runJson4sNative:·gc.time                              avgt   10       97.000                   ms

[info] BigJsonBenchmarkSerialize.runPlayJson:·gc.alloc.rate.norm                       avgt   10  3757471.816 ±     72.827    B/op
[info] BigJsonBenchmarkSerialize.runPlayJson:·gc.count                                 avgt   10      159.000               counts
[info] BigJsonBenchmarkSerialize.runPlayJson:·gc.time                                  avgt   10       91.000                   ms

[info] BigJsonBenchmarkSerialize.runSphereJson:·gc.alloc.rate.norm                     avgt   10   631608.623 ±      2.181    B/op
[info] BigJsonBenchmarkSerialize.runSphereJson:·gc.count                               avgt   10      137.000               counts
[info] BigJsonBenchmarkSerialize.runSphereJson:·gc.time                                avgt   10       86.000                   ms

[info] BigJsonBenchmarkSerialize.runSprayJson:·gc.alloc.rate.norm                      avgt   10   724208.693 ±      2.432    B/op
[info] BigJsonBenchmarkSerialize.runSprayJson:·gc.count                                avgt   10      192.000               counts
[info] BigJsonBenchmarkSerialize.runSprayJson:·gc.time                                 avgt   10       94.000                   ms
```
