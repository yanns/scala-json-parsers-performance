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
[info] Benchmark                                      Mode  Cnt   Score   Error  Units
[info] BigJsonBenchmarkDeserialize.runJson4sNative    avgt   10   1,785 ±  0,121  ms/op
[info] BigJsonBenchmarkDeserialize.runPlayJson        avgt   10   1,700 ±  0,225  ms/op
[info] BigJsonBenchmarkDeserialize.runJson4sJackson   avgt   10   1,677 ±  0,031  ms/op
[info] BigJsonBenchmarkDeserialize.runArgonautJson    avgt   10   1,067 ±  0,037  ms/op
[info] BigJsonBenchmarkDeserialize.runSphereJson      avgt   10   0,611 ±  0,154  ms/op
[info] BigJsonBenchmarkDeserialize.runCirce           avgt   10   0,491 ±  0,012  ms/op
[info] BigJsonBenchmarkDeserialize.runSprayJson       avgt   10   0,436 ±  0,038  ms/op
[info] BigJsonBenchmarkDeserialize.runJacksonParsing  avgt   10   0,237 ±  0,004  ms/op
[info] BigJsonBenchmarkDeserialize.runJsoniter        avgt   10   0,128 ±  0,002  ms/op
```
_ordered (lower is better)_

## Serialization (case class -> String)

```
[info] Benchmark                                      Mode  Cnt   Score   Error  Units
[info] BigJsonBenchmarkSerialize.runJson4sJackson     avgt   10   1,251 ±  0,021  ms/op
[info] BigJsonBenchmarkSerialize.runJson4sNative      avgt   10   1,232 ±  0,031  ms/op
[info] BigJsonBenchmarkSerialize.runArgonautJson      avgt   10   1,150 ±  0,027  ms/op
[info] BigJsonBenchmarkSerialize.runPlayJson          avgt   10   0,936 ±  0,206  ms/op
[info] BigJsonBenchmarkSerialize.runCirce             avgt   10   0,708 ±  0,147  ms/op
[info] BigJsonBenchmarkSerialize.runSprayJson         avgt   10   0,541 ±  0,098  ms/op
[info] BigJsonBenchmarkSerialize.runSphereJson        avgt   10   0,390 ±  0,177  ms/op
[info] BigJsonBenchmarkSerialize.runJacksonParsing    avgt   10   0,113 ±  0,017  ms/op
[info] BigJsonBenchmarkSerialize.runJsoniter          avgt   10   0,076 ±  0,013  ms/op
```
_ordered (lower is better)_

# Pressure on the GC

## Run with

    jmh:run -i 10 -wi 10 -f1 -t1 -prof gc

## Deserialization (String -> case class)

```
[info] Benchmark                                                          Mode  Cnt        Score        Error   Units
[info] BigJsonBenchmarkDeserialize.runArgonautJson:·gc.alloc.rate.norm    avgt   10  2843273,620 ±      5,573    B/op
[info] BigJsonBenchmarkDeserialize.runArgonautJson:·gc.count              avgt   10      116,000               counts
[info] BigJsonBenchmarkDeserialize.runArgonautJson:·gc.time               avgt   10       97,000                   ms

[info] BigJsonBenchmarkDeserialize.runCirce:·gc.alloc.rate.norm           avgt   10  1116704,716 ±      2,435    B/op
[info] BigJsonBenchmarkDeserialize.runCirce:·gc.count                     avgt   10      119,000               counts
[info] BigJsonBenchmarkDeserialize.runCirce:·gc.time                      avgt   10       93,000                   ms

[info] BigJsonBenchmarkDeserialize.runJacksonParsing:·gc.alloc.rate.norm  avgt   10   123528,033 ±     12,657    B/op
[info] BigJsonBenchmarkDeserialize.runJacksonParsing:·gc.count            avgt   10       33,000               counts
[info] BigJsonBenchmarkDeserialize.runJacksonParsing:·gc.time             avgt   10       22,000                   ms

[info] BigJsonBenchmarkDeserialize.runJson4sJackson:·gc.alloc.rate.norm   avgt   10  2145450,477 ±     66,969    B/op
[info] BigJsonBenchmarkDeserialize.runJson4sJackson:·gc.count             avgt   10       67,000               counts
[info] BigJsonBenchmarkDeserialize.runJson4sJackson:·gc.time              avgt   10       62,000                   ms

[info] BigJsonBenchmarkDeserialize.runJson4sNative:·gc.alloc.rate.norm    avgt   10  2513443,744 ±     69,035    B/op
[info] BigJsonBenchmarkDeserialize.runJson4sNative:·gc.count              avgt   10       89,000               counts
[info] BigJsonBenchmarkDeserialize.runJson4sNative:·gc.time               avgt   10       83,000                   ms

[info] BigJsonBenchmarkDeserialize.runJsoniter:·gc.alloc.rate.norm        avgt   10   192345,065 ±     12,036    B/op
[info] BigJsonBenchmarkDeserialize.runJsoniter:·gc.count                  avgt   10       95,000               counts
[info] BigJsonBenchmarkDeserialize.runJsoniter:·gc.time                   avgt   10       58,000                   ms

[info] BigJsonBenchmarkDeserialize.runPlayJson:·gc.alloc.rate.norm        avgt   10  2944850,680 ±     81,099    B/op
[info] BigJsonBenchmarkDeserialize.runPlayJson:·gc.count                  avgt   10       75,000               counts
[info] BigJsonBenchmarkDeserialize.runPlayJson:·gc.time                   avgt   10       73,000                   ms

[info] BigJsonBenchmarkDeserialize.runSphereJson:·gc.alloc.rate.norm      avgt   10   877024,809 ±      2,654    B/op
[info] BigJsonBenchmarkDeserialize.runSphereJson:·gc.count                avgt   10       64,000               counts
[info] BigJsonBenchmarkDeserialize.runSphereJson:·gc.time                 avgt   10       63,000                   ms

[info] BigJsonBenchmarkDeserialize.runSprayJson:·gc.alloc.rate.norm       avgt   10   403672,643 ±      2,202    B/op
[info] BigJsonBenchmarkDeserialize.runSprayJson:·gc.count                 avgt   10       59,000               counts
[info] BigJsonBenchmarkDeserialize.runSprayJson:·gc.time                  avgt   10       42,000                   ms
```
_not ordered (lower is better)_

## Serialization (case class -> String):

```
[info] Benchmark                                                          Mode  Cnt        Score        Error   Units
[info] BigJsonBenchmarkSerialize.runArgonautJson:·gc.alloc.rate.norm      avgt   10  2057193,690 ±      5,771    B/op
[info] BigJsonBenchmarkSerialize.runArgonautJson:·gc.count                avgt   10      113,000               counts
[info] BigJsonBenchmarkSerialize.runArgonautJson:·gc.time                 avgt   10       93,000                   ms

[info] BigJsonBenchmarkSerialize.runCirce:·gc.alloc.rate.norm             avgt   10  1060209,126 ±      4,016    B/op
[info] BigJsonBenchmarkSerialize.runCirce:·gc.count                       avgt   10       96,000               counts
[info] BigJsonBenchmarkSerialize.runCirce:·gc.time                        avgt   10       73,000                   ms

[info] BigJsonBenchmarkSerialize.runJacksonParsing:·gc.alloc.rate.norm    avgt   10    46439,947 ±      1,167    B/op
[info] BigJsonBenchmarkSerialize.runJacksonParsing:·gc.count              avgt   10       26,000               counts
[info] BigJsonBenchmarkSerialize.runJacksonParsing:·gc.time               avgt   10       21,000                   ms

[info] BigJsonBenchmarkSerialize.runJson4sJackson:·gc.alloc.rate.norm     avgt   10  1351249,832 ±      6,288    B/op
[info] BigJsonBenchmarkSerialize.runJson4sJackson:·gc.count               avgt   10       68,000               counts
[info] BigJsonBenchmarkSerialize.runJson4sJackson:·gc.time                avgt   10       53,000                   ms

[info] BigJsonBenchmarkSerialize.runJson4sNative:·gc.alloc.rate.norm      avgt   10  1983353,820 ±      6,271    B/op
[info] BigJsonBenchmarkSerialize.runJson4sNative:·gc.count                avgt   10      102,000               counts
[info] BigJsonBenchmarkSerialize.runJson4sNative:·gc.time                 avgt   10       66,000                   ms

[info] BigJsonBenchmarkSerialize.runJsoniter:·gc.alloc.rate.norm          avgt   10   113000,111 ±      0,383    B/op
[info] BigJsonBenchmarkSerialize.runJsoniter:·gc.count                    avgt   10       96,000               counts
[info] BigJsonBenchmarkSerialize.runJsoniter:·gc.time                     avgt   10       56,000                   ms

[info] BigJsonBenchmarkSerialize.runPlayJson:·gc.alloc.rate.norm          avgt   10  1529761,419 ±      4,898    B/op
[info] BigJsonBenchmarkSerialize.runPlayJson:·gc.count                    avgt   10       68,000               counts
[info] BigJsonBenchmarkSerialize.runPlayJson:·gc.time                     avgt   10       69,000                   ms

[info] BigJsonBenchmarkSerialize.runSphereJson:·gc.alloc.rate.norm        avgt   10   543760,895 ±      3,581    B/op
[info] BigJsonBenchmarkSerialize.runSphereJson:·gc.count                  avgt   10       64,000               counts
[info] BigJsonBenchmarkSerialize.runSphereJson:·gc.time                   avgt   10       70,000                   ms

[info] BigJsonBenchmarkSerialize.runSprayJson:·gc.alloc.rate.norm         avgt   10   626608,731 ±      2,413    B/op
[info] BigJsonBenchmarkSerialize.runSprayJson:·gc.count                   avgt   10       74,000               counts
[info] BigJsonBenchmarkSerialize.runSprayJson:·gc.time                    avgt   10       50,000                   ms
```
_not ordered (lower is better)_
