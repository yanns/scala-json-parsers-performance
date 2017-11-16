Scala JSON parser performance comparator
========================================

Compares performance of several JSON parsers used in Scala:

- [argonaut](http://argonaut.io/)
- [jackson](http://wiki.fasterxml.com/JacksonHome) with [scala module](https://github.com/FasterXML/jackson-module-scala)
- [json4s with jackson](https://github.com/json4s/json4s#jackson)
- [json4s native](https://github.com/json4s/json4s)
- [play-json](https://www.playframework.com/documentation/latest/ScalaJson), used in [Play Framework](https://www.playframework.com/)
- [sphere-json](https://github.com/sphereio/sphere-scala-libs/tree/master/json)
- [spray-json](https://github.com/spray/spray-json)

The test case is to deserialize a json into a case class and to serialize back to json.

The performances are measured with [JMH](https://github.com/ktoso/sbt-jmh)

# How to run it yourself?

Clone the project and start `sbt`.


# Performance test

## Run with

    jmh:run -i 10 -wi 10 -f1 -t1

(for a quick feedback:)

    jmh:run -i 2 -wi 2 -f1 -t1


## Deserialization (String -> case class)

```
[info] Benchmark                                      Mode  Cnt   Score    Error  Units
[info] BigJsonBenchmarkDeserialize.runArgonautJson    avgt   10   1,108 ±  0,064  ms/op
[info] BigJsonBenchmarkDeserialize.runJacksonParsing  avgt   10   0,237 ±  0,012  ms/op
[info] BigJsonBenchmarkDeserialize.runJson4sJackson   avgt   10   1,771 ±  0,093  ms/op
[info] BigJsonBenchmarkDeserialize.runJson4sNative    avgt   10   1,626 ±  0,073  ms/op
[info] BigJsonBenchmarkDeserialize.runPlayJson        avgt   10   1,471 ±  0,063  ms/op
[info] BigJsonBenchmarkDeserialize.runSphereJson      avgt   10   0,601 ±  0,020  ms/op
[info] BigJsonBenchmarkDeserialize.runSprayJson       avgt   10   0,406 ±  0,029  ms/op
```
_(lower is better)_

## Serialization (case class -> String)

```
[info] Benchmark                                      Mode  Cnt   Score    Error  Units
[info] BigJsonBenchmarkSerialize.runArgonautJson      avgt   10   0,772 ±  0,030  ms/op
[info] BigJsonBenchmarkSerialize.runJacksonParsing    avgt   10   0,104 ±  0,007  ms/op
[info] BigJsonBenchmarkSerialize.runJson4sJackson     avgt   10   1,142 ±  0,053  ms/op
[info] BigJsonBenchmarkSerialize.runJson4sNative      avgt   10   1,189 ±  0,041  ms/op
[info] BigJsonBenchmarkSerialize.runPlayJson          avgt   10   0,742 ±  0,031  ms/op
[info] BigJsonBenchmarkSerialize.runSphereJson        avgt   10   0,311 ±  0,014  ms/op
[info] BigJsonBenchmarkSerialize.runSprayJson         avgt   10   0,483 ±  0,025  ms/op
_(lower is better)_

# Pressure on the GC

## Run with

    jmh:run -i 10 -wi 10 -f1 -t1 -prof gc

## Deserialization (String -> case class)

```
[info] Benchmark                                                                       Mode  Cnt        Score        Error   Units
[info] BigJsonBenchmarkDeserialize.runArgonautJson:·gc.alloc.rate.norm                 avgt   10  3171577.759 ±      6.209    B/op
[info] BigJsonBenchmarkDeserialize.runArgonautJson:·gc.count                           avgt   10      107.000               counts
[info] BigJsonBenchmarkDeserialize.runArgonautJson:·gc.time                            avgt   10       87.000                   ms

[info] BigJsonBenchmarkDeserialize.runJacksonParsing:·gc.alloc.rate.norm               avgt   10   131488.389 ±      1.367    B/op
[info] BigJsonBenchmarkDeserialize.runJacksonParsing:·gc.count                         avgt   10      128.000               counts
[info] BigJsonBenchmarkDeserialize.runJacksonParsing:·gc.time                          avgt   10       75.000                   ms

[info] BigJsonBenchmarkDeserialize.runJson4sJackson:·gc.alloc.rate.norm                avgt   10  2049563.979 ±     10.685    B/op
[info] BigJsonBenchmarkDeserialize.runJson4sJackson:·gc.count                          avgt   10      119.000               counts
[info] BigJsonBenchmarkDeserialize.runJson4sJackson:·gc.time                           avgt   10       95.000                   ms

[info] BigJsonBenchmarkDeserialize.runJson4sNative:·gc.alloc.rate.norm                 avgt   10  2521971.209 ±      8.691    B/op
[info] BigJsonBenchmarkDeserialize.runJson4sNative:·gc.count                           avgt   10      112.000               counts
[info] BigJsonBenchmarkDeserialize.runJson4sNative:·gc.time                            avgt   10       95.000                   ms

[info] BigJsonBenchmarkDeserialize.runPlayJson:·gc.alloc.rate.norm                     avgt   10  3024493.997 ±     17.548    B/op
[info] BigJsonBenchmarkDeserialize.runPlayJson:·gc.count                               avgt   10       78.000               counts
[info] BigJsonBenchmarkDeserialize.runPlayJson:·gc.time                                avgt   10       91.000                   ms

[info] BigJsonBenchmarkDeserialize.runSphereJson:·gc.alloc.rate.norm                   avgt   10  2130585.410 ±      4.977    B/op
[info] BigJsonBenchmarkDeserialize.runSphereJson:·gc.count                             avgt   10       87.000               counts
[info] BigJsonBenchmarkDeserialize.runSphereJson:·gc.time                              avgt   10       79.000                   ms

[info] BigJsonBenchmarkDeserialize.runSprayJson:·gc.alloc.rate.norm                    avgt   10   475744.614 ±      2.160    B/op
[info] BigJsonBenchmarkDeserialize.runSprayJson:·gc.count                              avgt   10       95.000               counts
[info] BigJsonBenchmarkDeserialize.runSprayJson:·gc.time                               avgt   10       61.000                   ms
```
_(lower is better)_

## Serialization (case class -> String):

```
[info] Benchmark                                                                       Mode  Cnt        Score        Error   Units
[info] BigJsonBenchmarkSerialize.runArgonautJson:·gc.alloc.rate.norm                   avgt   10  1581537.178 ±      4.197    B/op
[info] BigJsonBenchmarkSerialize.runArgonautJson:·gc.count                             avgt   10      137.000               counts
[info] BigJsonBenchmarkSerialize.runArgonautJson:·gc.time                              avgt   10       94.000                   ms

[info] BigJsonBenchmarkSerialize.runJacksonParsing:·gc.alloc.rate.norm                 avgt   10   112240.180 ±      0.621    B/op
[info] BigJsonBenchmarkSerialize.runJacksonParsing:·gc.count                           avgt   10      130.000               counts
[info] BigJsonBenchmarkSerialize.runJacksonParsing:·gc.time                            avgt   10       77.000                   ms

[info] BigJsonBenchmarkSerialize.runJson4sJackson:·gc.alloc.rate.norm                  avgt   10  1297273.777 ±      6.214    B/op
[info] BigJsonBenchmarkSerialize.runJson4sJackson:·gc.count                            avgt   10      115.000               counts
[info] BigJsonBenchmarkSerialize.runJson4sJackson:·gc.time                             avgt   10       83.000                   ms

[info] BigJsonBenchmarkSerialize.runJson4sNative:·gc.alloc.rate.norm                   avgt   10  1637497.656 ±      5.916    B/op
[info] BigJsonBenchmarkSerialize.runJson4sNative:·gc.count                             avgt   10      128.000               counts
[info] BigJsonBenchmarkSerialize.runJson4sNative:·gc.time                              avgt   10       80.000                   ms

[info] BigJsonBenchmarkSerialize.runPlayJson:·gc.alloc.rate.norm                       avgt   10  3653432.630 ±     73.305    B/op
[info] BigJsonBenchmarkSerialize.runPlayJson:·gc.count                                 avgt   10      130.000               counts
[info] BigJsonBenchmarkSerialize.runPlayJson:·gc.time                                  avgt   10       90.000                   ms

[info] BigJsonBenchmarkSerialize.runSphereJson:·gc.alloc.rate.norm                     avgt   10   607608.644 ±      2.261    B/op
[info] BigJsonBenchmarkSerialize.runSphereJson:·gc.count                               avgt   10       84.000               counts
[info] BigJsonBenchmarkSerialize.runSphereJson:·gc.time                                avgt   10       58.000                   ms

[info] BigJsonBenchmarkSerialize.runSprayJson:·gc.alloc.rate.norm                      avgt   10   724208.713 ±      2.479    B/op
[info] BigJsonBenchmarkSerialize.runSprayJson:·gc.count                                avgt   10      134.000               counts
[info] BigJsonBenchmarkSerialize.runSprayJson:·gc.time                                 avgt   10       75.000                   ms
```
_(lower is better)_
