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
Benchmark                                      Mode  Cnt   Score   Error  Units
BigJsonBenchmarkDeserialize.runJson4sNative    avgt   10   1,753 ±  0,034  ms/op
BigJsonBenchmarkDeserialize.runJson4sJackson   avgt   10   1,652 ±  0,040  ms/op
BigJsonBenchmarkDeserialize.runPlayJson        avgt   10   1,539 ±  0,029  ms/op
BigJsonBenchmarkDeserialize.runArgonautJson    avgt   10   1,221 ±  0,124  ms/op
BigJsonBenchmarkDeserialize.runCirce           avgt   10   0,602 ±  0,163  ms/op
BigJsonBenchmarkDeserialize.runSphereJson      avgt   10   0,536 ±  0,009  ms/op
BigJsonBenchmarkDeserialize.runSprayJson       avgt   10   0,414 ±  0,005  ms/op
BigJsonBenchmarkDeserialize.runJacksonParsing  avgt   10   0,260 ±  0,015  ms/op
BigJsonBenchmarkDeserialize.runJsoniter        avgt   10   0,120 ±  0,002  ms/op
```
_ordered (lower is better)_

## Serialization (case class -> String)

```
Benchmark                                      Mode  Cnt   Score   Error  Units
BigJsonBenchmarkSerialize.runArgonautJson      avgt   10   1,138 ±  0,021  ms/op
BigJsonBenchmarkSerialize.runJson4sNative      avgt   10   1,168 ±  0,031  ms/op
BigJsonBenchmarkSerialize.runJson4sJackson     avgt   10   1,013 ±  0,021  ms/op
BigJsonBenchmarkSerialize.runPlayJson          avgt   10   0,765 ±  0,019  ms/op
BigJsonBenchmarkSerialize.runCirce             avgt   10   0,587 ±  0,014  ms/op
BigJsonBenchmarkSerialize.runSprayJson         avgt   10   0,487 ±  0,008  ms/op
BigJsonBenchmarkSerialize.runSphereJson        avgt   10   0,313 ±  0,009  ms/op
BigJsonBenchmarkSerialize.runJacksonParsing    avgt   10   0,104 ±  0,002  ms/op
BigJsonBenchmarkSerialize.runJsoniter          avgt   10   0,071 ±  0,001  ms/op
```
_ordered (lower is better)_

# Pressure on the GC

## Run with

    jmh:run -i 10 -wi 10 -f1 -t1 -prof gc

## Deserialization (String -> case class)

```
Benchmark                                                          Mode  Cnt        Score        Error   Units

BigJsonBenchmarkDeserialize.runPlayJson:·gc.alloc.rate.norm        avgt   10  2911587,610 ±     24,514    B/op
BigJsonBenchmarkDeserialize.runPlayJson:·gc.count                  avgt   10       83,000               counts
BigJsonBenchmarkDeserialize.runPlayJson:·gc.time                   avgt   10       72,000                   ms

BigJsonBenchmarkDeserialize.runArgonautJson:·gc.alloc.rate.norm    avgt   10  2866649,677 ±      5,560    B/op
BigJsonBenchmarkDeserialize.runArgonautJson:·gc.count              avgt   10      102,000               counts
BigJsonBenchmarkDeserialize.runArgonautJson:·gc.time               avgt   10      105,000                   ms

BigJsonBenchmarkDeserialize.runJson4sNative:·gc.alloc.rate.norm    avgt   10  2513426,093 ±     78,900    B/op
BigJsonBenchmarkDeserialize.runJson4sNative:·gc.count              avgt   10       75,000               counts
BigJsonBenchmarkDeserialize.runJson4sNative:·gc.time               avgt   10       74,000                   ms

BigJsonBenchmarkDeserialize.runJson4sJackson:·gc.alloc.rate.norm   avgt   10  2033341,095 ±     85,851    B/op
BigJsonBenchmarkDeserialize.runJson4sJackson:·gc.count             avgt   10       53,000               counts
BigJsonBenchmarkDeserialize.runJson4sJackson:·gc.time              avgt   10       51,000                   ms

BigJsonBenchmarkDeserialize.runCirce:·gc.alloc.rate.norm           avgt   10  1115944,860 ±      2,935    B/op
BigJsonBenchmarkDeserialize.runCirce:·gc.count                     avgt   10       82,000               counts
BigJsonBenchmarkDeserialize.runCirce:·gc.time                      avgt   10       96,000                   ms

BigJsonBenchmarkDeserialize.runSphereJson:·gc.alloc.rate.norm      avgt   10   875920,782 ±      2,688    B/op
BigJsonBenchmarkDeserialize.runSphereJson:·gc.count                avgt   10       70,000               counts
BigJsonBenchmarkDeserialize.runSphereJson:·gc.time                 avgt   10       62,000                   ms

BigJsonBenchmarkDeserialize.runSprayJson:·gc.alloc.rate.norm       avgt   10   396888,610 ±      2,107    B/op
BigJsonBenchmarkDeserialize.runSprayJson:·gc.count                 avgt   10       61,000               counts
BigJsonBenchmarkDeserialize.runSprayJson:·gc.time                  avgt   10       45,000                   ms

BigJsonBenchmarkDeserialize.runJsoniter:·gc.alloc.rate.norm        avgt   10   192285,306 ±     11,319    B/op
BigJsonBenchmarkDeserialize.runJsoniter:·gc.count                  avgt   10       84,000               counts
BigJsonBenchmarkDeserialize.runJsoniter:·gc.time                   avgt   10       62,000                   ms

BigJsonBenchmarkDeserialize.runJacksonParsing:·gc.alloc.rate.norm  avgt   10   171568,377 ±      1,293    B/op
BigJsonBenchmarkDeserialize.runJacksonParsing:·gc.count            avgt   10       41,000               counts
BigJsonBenchmarkDeserialize.runJacksonParsing:·gc.time             avgt   10       33,000                   ms
```
_ordered (lower is better)_

## Serialization (case class -> String):

```
Benchmark                                                          Mode  Cnt        Score        Error   Units

BigJsonBenchmarkSerialize.runArgonautJson:·gc.alloc.rate.norm      avgt   10  2056569,646 ±      5,637    B/op
BigJsonBenchmarkSerialize.runArgonautJson:·gc.count                avgt   10       95,000               counts
BigJsonBenchmarkSerialize.runArgonautJson:·gc.time                 avgt   10       81,000                   ms

BigJsonBenchmarkSerialize.runJson4sNative:·gc.alloc.rate.norm      avgt   10  1951337,726 ±      5,991    B/op
BigJsonBenchmarkSerialize.runJson4sNative:·gc.count                avgt   10       87,000               counts
BigJsonBenchmarkSerialize.runJson4sNative:·gc.time                 avgt   10       67,000                   ms

BigJsonBenchmarkSerialize.runPlayJson:·gc.alloc.rate.norm          avgt   10  1528529,107 ±      3,774    B/op
BigJsonBenchmarkSerialize.runPlayJson:·gc.count                    avgt   10       88,000               counts
BigJsonBenchmarkSerialize.runPlayJson:·gc.time                     avgt   10       71,000                   ms

BigJsonBenchmarkSerialize.runJson4sJackson:·gc.alloc.rate.norm     avgt   10  1151113,473 ±      5,044    B/op
BigJsonBenchmarkSerialize.runJson4sJackson:·gc.count               avgt   10       60,000               counts
BigJsonBenchmarkSerialize.runJson4sJackson:·gc.time                avgt   10       53,000                   ms

BigJsonBenchmarkSerialize.runCirce:·gc.alloc.rate.norm             avgt   10  1059392,834 ±      2,821    B/op
BigJsonBenchmarkSerialize.runCirce:·gc.count                       avgt   10       95,000               counts
BigJsonBenchmarkSerialize.runCirce:·gc.time                        avgt   10       73,000                   ms

BigJsonBenchmarkSerialize.runSprayJson:·gc.alloc.rate.norm         avgt   10   603824,701 ±      2,404    B/op
BigJsonBenchmarkSerialize.runSprayJson:·gc.count                   avgt   10       65,000               counts
BigJsonBenchmarkSerialize.runSprayJson:·gc.time                    avgt   10       49,000                   ms

BigJsonBenchmarkSerialize.runSphereJson:·gc.alloc.rate.norm        avgt   10   542632,455 ±      1,516    B/op
BigJsonBenchmarkSerialize.runSphereJson:·gc.count                  avgt   10       75,000               counts
BigJsonBenchmarkSerialize.runSphereJson:·gc.time                   avgt   10       70,000                   ms

BigJsonBenchmarkSerialize.runJsoniter:·gc.alloc.rate.norm          avgt   10   112944,102 ±      0,351    B/op
BigJsonBenchmarkSerialize.runJsoniter:·gc.count                    avgt   10       85,000               counts
BigJsonBenchmarkSerialize.runJsoniter:·gc.time                     avgt   10       64,000                   ms

BigJsonBenchmarkSerialize.runJacksonParsing:·gc.alloc.rate.norm    avgt   10    46424,150 ±      0,505    B/op
BigJsonBenchmarkSerialize.runJacksonParsing:·gc.count              avgt   10       29,000               counts
BigJsonBenchmarkSerialize.runJacksonParsing:·gc.time               avgt   10       23,000                   ms
```
_ordered (lower is better)_
