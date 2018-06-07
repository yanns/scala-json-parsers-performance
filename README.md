Scala JSON parser performance comparator
========================================

Compares performance of several JSON parsers used in Scala:

- [argonaut](http://argonaut.io/)
- [circe](https://circe.github.io/circe/)
- [jackson](http://wiki.fasterxml.com/JacksonHome) with [scala module](https://github.com/FasterXML/jackson-module-scala)
- [json4s with jackson](https://github.com/json4s/json4s#jackson)
- [json4s native](https://github.com/json4s/json4s)
- [play-json](https://www.playframework.com/documentation/latest/ScalaJson), used in [Play Framework](https://www.playframework.com/)
- [sphere-json](https://github.com/sphereio/sphere-scala-libs/tree/master/json)
- [spray-json](https://github.com/spray/spray-json)
- [jsoniter-scala](https://github.com/plokhotnyuk/jsoniter-scala)
- [uJson](http://www.lihaoyi.com/upickle/#uJson)

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
Benchmark                                      Mode  Cnt   Score    Error  Units
BigJsonBenchmarkDeserialize.runJson4sNative    avgt   10   1,704 ±  0,014  ms/op
BigJsonBenchmarkDeserialize.runPlayJson        avgt   10   1,401 ±  0,025  ms/op
BigJsonBenchmarkDeserialize.runJson4sJackson   avgt   10   1,306 ±  0,016  ms/op
BigJsonBenchmarkDeserialize.runArgonautJson    avgt   10   1,025 ±  0,014  ms/op
BigJsonBenchmarkDeserialize.runCirce           avgt   10   0,433 ±  0,009  ms/op
BigJsonBenchmarkDeserialize.runSphereJson      avgt   10   0,393 ±  0,008  ms/op
BigJsonBenchmarkDeserialize.runSprayJson       avgt   10   0,380 ±  0,021  ms/op
BigJsonBenchmarkDeserialize.runUJson           avgt   10   0,335 ±  0,029  ms/op
BigJsonBenchmarkDeserialize.runJacksonParsing  avgt   10   0,225 ±  0,002  ms/op
BigJsonBenchmarkDeserialize.runJsoniter        avgt   10   0,110 ±  0,001  ms/op
```
_ordered (lower is better)_

## Serialization (case class -> String)

```
Benchmark                                      Mode  Cnt   Score   Error  Units
BigJsonBenchmarkSerialize.runJson4sNative      avgt   10   1,055 ±  0,019  ms/op
BigJsonBenchmarkSerialize.runJson4sJackson     avgt   10   0,989 ±  0,018  ms/op
BigJsonBenchmarkSerialize.runArgonautJson      avgt   10   0,961 ±  0,007  ms/op
BigJsonBenchmarkSerialize.runPlayJson          avgt   10   0,692 ±  0,008  ms/op
BigJsonBenchmarkSerialize.runCirce             avgt   10   0,536 ±  0,006  ms/op
BigJsonBenchmarkSerialize.runSprayJson         avgt   10   0,408 ±  0,007  ms/op
BigJsonBenchmarkSerialize.runUJson             avgt   10   0,310 ±  0,007  ms/op
BigJsonBenchmarkSerialize.runSphereJson        avgt   10   0,271 ±  0,018  ms/op
BigJsonBenchmarkSerialize.runJacksonParsing    avgt   10   0,098 ±  0,001  ms/op
BigJsonBenchmarkSerialize.runJsoniter          avgt   10   0,070 ±  0,001  ms/op
```
_ordered (lower is better)_

# Pressure on the GC

## Run with

    jmh:run -i 10 -wi 10 -f1 -t1 -prof gc

## Deserialization (String -> case class)

```
Benchmark                                                          Mode  Cnt        Score        Error   Units
BigJsonBenchmarkDeserialize.runPlayJson:·gc.alloc.rate.norm        avgt   10  2943599,396 ±     22,306    B/op
BigJsonBenchmarkDeserialize.runPlayJson:·gc.count                  avgt   10       91,000               counts
BigJsonBenchmarkDeserialize.runPlayJson:·gc.time                   avgt   10       83,000                   ms

BigJsonBenchmarkDeserialize.runArgonautJson:·gc.alloc.rate.norm    avgt   10  2914641,479 ±      5,076    B/op
BigJsonBenchmarkDeserialize.runArgonautJson:·gc.count              avgt   10      103,000               counts
BigJsonBenchmarkDeserialize.runArgonautJson:·gc.time               avgt   10      102,000                   ms

BigJsonBenchmarkDeserialize.runJson4sNative:·gc.alloc.rate.norm    avgt   10  2513419,635 ±     65,122    B/op
BigJsonBenchmarkDeserialize.runJson4sNative:·gc.count              avgt   10       77,000               counts
BigJsonBenchmarkDeserialize.runJson4sNative:·gc.time               avgt   10       80,000                   ms

BigJsonBenchmarkDeserialize.runJson4sJackson:·gc.alloc.rate.norm   avgt   10  1785089,933 ±      6,242    B/op
BigJsonBenchmarkDeserialize.runJson4sJackson:·gc.count             avgt   10       72,000               counts
BigJsonBenchmarkDeserialize.runJson4sJackson:·gc.time              avgt   10       72,000                   ms

BigJsonBenchmarkDeserialize.runCirce:·gc.alloc.rate.norm           avgt   10  1115944,630 ±      2,169    B/op
BigJsonBenchmarkDeserialize.runCirce:·gc.count                     avgt   10      112,000               counts
BigJsonBenchmarkDeserialize.runCirce:·gc.time                      avgt   10      102,000                   ms

BigJsonBenchmarkDeserialize.runSphereJson:·gc.alloc.rate.norm      avgt   10   699640,573 ±      1,959    B/op
BigJsonBenchmarkDeserialize.runSphereJson:·gc.count                avgt   10       77,000               counts
BigJsonBenchmarkDeserialize.runSphereJson:·gc.time                 avgt   10       71,000                   ms

BigJsonBenchmarkDeserialize.runUJson:·gc.alloc.rate.norm           avgt   10   646640,473 ±      1,612    B/op
BigJsonBenchmarkDeserialize.runUJson:·gc.count                     avgt   10      102,000               counts
BigJsonBenchmarkDeserialize.runUJson:·gc.time                      avgt   10       78,000                   ms

BigJsonBenchmarkDeserialize.runSprayJson:·gc.alloc.rate.norm       avgt   10   396872,544 ±      1,853    B/op
BigJsonBenchmarkDeserialize.runSprayJson:·gc.count                 avgt   10       66,000               counts
BigJsonBenchmarkDeserialize.runSprayJson:·gc.time                  avgt   10       49,000                   ms

BigJsonBenchmarkDeserialize.runJsoniter:·gc.alloc.rate.norm        avgt   10   192283,853 ±      9,990    B/op
BigJsonBenchmarkDeserialize.runJsoniter:·gc.count                  avgt   10       76,000               counts
BigJsonBenchmarkDeserialize.runJsoniter:·gc.time                   avgt   10       59,000                   ms

BigJsonBenchmarkDeserialize.runJacksonParsing:·gc.alloc.rate.norm  avgt   10   171568,348 ±      1,224    B/op
BigJsonBenchmarkDeserialize.runJacksonParsing:·gc.count            avgt   10       48,000               counts
BigJsonBenchmarkDeserialize.runJacksonParsing:·gc.time             avgt   10       40,000                   ms
```
_ordered (lower is better)_

## Serialization (case class -> String):

```
Benchmark                                                          Mode  Cnt        Score        Error   Units

BigJsonBenchmarkSerialize.runArgonautJson:·gc.alloc.rate.norm      avgt   10  2056393,390 ±      4,767    B/op
BigJsonBenchmarkSerialize.runArgonautJson:·gc.count                avgt   10      112,000               counts
BigJsonBenchmarkSerialize.runArgonautJson:·gc.time                 avgt   10      102,000                   ms

BigJsonBenchmarkSerialize.runJson4sNative:·gc.alloc.rate.norm      avgt   10  1951337,529 ±      5,248    B/op
BigJsonBenchmarkSerialize.runJson4sNative:·gc.count                avgt   10      117,000               counts
BigJsonBenchmarkSerialize.runJson4sNative:·gc.time                 avgt   10       86,000                   ms

BigJsonBenchmarkSerialize.runPlayJson:·gc.alloc.rate.norm          avgt   10  1528553,002 ±      3,450    B/op
BigJsonBenchmarkSerialize.runPlayJson:·gc.count                    avgt   10       81,000               counts
BigJsonBenchmarkSerialize.runPlayJson:·gc.time                     avgt   10       72,000                   ms

BigJsonBenchmarkSerialize.runJson4sJackson:·gc.alloc.rate.norm     avgt   10  1175137,445 ±      4,978    B/op
BigJsonBenchmarkSerialize.runJson4sJackson:·gc.count               avgt   10       62,000               counts
BigJsonBenchmarkSerialize.runJson4sJackson:·gc.time                avgt   10       56,000                   ms

BigJsonBenchmarkSerialize.runCirce:·gc.alloc.rate.norm             avgt   10  1059392,783 ±      2,691    B/op
BigJsonBenchmarkSerialize.runCirce:·gc.count                       avgt   10       86,000               counts
BigJsonBenchmarkSerialize.runCirce:·gc.time                        avgt   10       72,000                   ms

BigJsonBenchmarkSerialize.runSprayJson:·gc.alloc.rate.norm         avgt   10   603808,586 ±      2,013    B/op
BigJsonBenchmarkSerialize.runSprayJson:·gc.count                   avgt   10       77,000               counts
BigJsonBenchmarkSerialize.runSprayJson:·gc.time                    avgt   10       60,000                   ms

BigJsonBenchmarkSerialize.runSphereJson:·gc.alloc.rate.norm        avgt   10   542632,423 ±      1,308    B/op
BigJsonBenchmarkSerialize.runSphereJson:·gc.count                  avgt   10       73,000               counts
BigJsonBenchmarkSerialize.runSphereJson:·gc.time                   avgt   10       76,000                   ms

BigJsonBenchmarkSerialize.runUJson:·gc.alloc.rate.norm             avgt   10   182072,593 ±      1,616    B/op
BigJsonBenchmarkSerialize.runUJson:·gc.count                       avgt   10       37,000               counts
BigJsonBenchmarkSerialize.runUJson:·gc.time                        avgt   10       29,000                   ms

BigJsonBenchmarkSerialize.runJsoniter:·gc.alloc.rate.norm          avgt   10   112856,102 ±      0,350    B/op
BigJsonBenchmarkSerialize.runJsoniter:·gc.count                    avgt   10       86,000               counts
BigJsonBenchmarkSerialize.runJsoniter:·gc.time                     avgt   10       66,000                   ms

BigJsonBenchmarkSerialize.runJacksonParsing:·gc.alloc.rate.norm    avgt   10    46424,140 ±      0,472    B/op
BigJsonBenchmarkSerialize.runJacksonParsing:·gc.count              avgt   10       30,000               counts
BigJsonBenchmarkSerialize.runJacksonParsing:·gc.time               avgt   10       25,000                   ms
```
_ordered (lower is better)_
