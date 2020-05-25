Scala JSON parser performance comparator
========================================

Compares performance of several JSON parsers used in Scala:

- [argonaut](http://argonaut.io/)
- [borer](https://sirthias.github.io/borer/)
- [circe](https://circe.github.io/circe/)
- [jackson](http://wiki.fasterxml.com/JacksonHome) with [scala module](https://github.com/FasterXML/jackson-module-scala)
- [json4s with jackson](https://github.com/json4s/json4s#jackson)
- [json4s native](https://github.com/json4s/json4s)
- [play-json](https://www.playframework.com/documentation/latest/ScalaJson), used in [Play Framework](https://www.playframework.com/)
- [sphere-json](https://github.com/sphereio/sphere-scala-libs/tree/master/json)
- [spray-json](https://github.com/spray/spray-json)
- [jsoniter-scala](https://github.com/plokhotnyuk/jsoniter-scala)
- [uPickle](http://www.lihaoyi.com/upickle)
- [weePickle](https://github.com/rallyhealth/weePickle)
- [refuel-json](https://github.com/giiita/refuel)

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
BigJsonBenchmarkDeserialize.runJson4sNative    avgt   10   1,262 ±  0,011  ms/op
BigJsonBenchmarkDeserialize.runJson4sJackson   avgt   10   1,101 ±  0,013  ms/op
BigJsonBenchmarkDeserialize.runArgonautJson    avgt   10   0,783 ±  0,018  ms/op
BigJsonBenchmarkDeserialize.runPlayJson        avgt   10   0,608 ±  0,011  ms/op
BigJsonBenchmarkDeserialize.runSprayJson       avgt   10   0,399 ±  0,011  ms/op
BigJsonBenchmarkDeserialize.runRefuelParsing   avgt   10   0,383 ±  0,007  ms/op
BigJsonBenchmarkDeserialize.runCirce           avgt   10   0,373 ±  0,008  ms/op
BigJsonBenchmarkDeserialize.runUPickle         avgt   10   0,335 ±  0,003  ms/op
BigJsonBenchmarkDeserialize.runSphereJson      avgt   10   0,316 ±  0,008  ms/op
BigJsonBenchmarkDeserialize.runWeePickle       avgt   10   0,218 ±  0,001  ms/op
BigJsonBenchmarkDeserialize.runJacksonParsing  avgt   10   0,186 ±  0,001  ms/op
BigJsonBenchmarkDeserialize.runBorer           avgt   10   0,156 ±  0,003  ms/op
BigJsonBenchmarkDeserialize.runJsoniter        avgt   10   0,078 ±  0,001  ms/op
```
_ordered (lower is better)_

## Serialization (case class -> String)

```
Benchmark                                      Mode  Cnt   Score    Error  Units
BigJsonBenchmarkSerialize.runJson4sNative      avgt   10   1,758 ±  0,009  ms/op
BigJsonBenchmarkSerialize.runJson4sJackson     avgt   10   1,351 ±  0,015  ms/op
BigJsonBenchmarkSerialize.runPlayJson          avgt   10   0,739 ±  0,004  ms/op
BigJsonBenchmarkSerialize.runRefuelParsing     avgt   10   0,725 ±  0,039  ms/op
BigJsonBenchmarkSerialize.runUPickle           avgt   10   0,671 ±  0,002  ms/op
BigJsonBenchmarkSerialize.runArgonautJson      avgt   10   0,436 ±  0,007  ms/op
BigJsonBenchmarkSerialize.runCirce             avgt   10   0,369 ±  0,002  ms/op
BigJsonBenchmarkSerialize.runSprayJson         avgt   10   0,312 ±  0,001  ms/op
BigJsonBenchmarkSerialize.runSphereJson        avgt   10   0,227 ±  0,001  ms/op
BigJsonBenchmarkSerialize.runBorer             avgt   10   0,156 ±  0,001  ms/op
BigJsonBenchmarkSerialize.runWeePickle         avgt   10   0,130 ±  0,002  ms/op
BigJsonBenchmarkSerialize.runJacksonParsing    avgt   10   0,091 ±  0,001  ms/op
BigJsonBenchmarkSerialize.runJsoniter          avgt   10   0,078 ±  0,001  ms/op
```
_ordered (lower is better)_

# Pressure on the GC

## Run with

    jmh:run -i 10 -wi 10 -f1 -t1 -prof gc

## Deserialization (String -> case class)

```
Benchmark                                                                       Mode  Cnt        Score        Error   Units

BigJsonBenchmarkDeserialize.runJson4sNative:·gc.alloc.rate.norm                 avgt   10  2973937,828 ±      6,240    B/op
BigJsonBenchmarkDeserialize.runJson4sNative:·gc.count                           avgt   10       59,000               counts
BigJsonBenchmarkDeserialize.runJson4sNative:·gc.time                            avgt   10       43,000                   ms

BigJsonBenchmarkDeserialize.runArgonautJson:·gc.alloc.rate.norm                 avgt   10  2690641,180 ±      4,096    B/op
BigJsonBenchmarkDeserialize.runArgonautJson:·gc.count                           avgt   10       86,000               counts
BigJsonBenchmarkDeserialize.runArgonautJson:·gc.time                            avgt   10       50,000                   ms

BigJsonBenchmarkDeserialize.runJson4sJackson:·gc.alloc.rate.norm                avgt   10  2129657,616 ±      5,438    B/op
BigJsonBenchmarkDeserialize.runJson4sJackson:·gc.count                          avgt   10       49,000               counts
BigJsonBenchmarkDeserialize.runJson4sJackson:·gc.time                           avgt   10       34,000                   ms

BigJsonBenchmarkDeserialize.runPlayJson:·gc.alloc.rate.norm                     avgt   10  1914960,898 ±      3,088    B/op
BigJsonBenchmarkDeserialize.runPlayJson:·gc.count                               avgt   10       78,000               counts
BigJsonBenchmarkDeserialize.runPlayJson:·gc.time                                avgt   10       39,000                   ms

BigJsonBenchmarkDeserialize.runRefuelParsing:·gc.alloc.rate.norm                avgt   10  1064552,577 ±      1,988    B/op
BigJsonBenchmarkDeserialize.runRefuelParsing:·gc.count                          avgt   10       69,000               counts
BigJsonBenchmarkDeserialize.runRefuelParsing:·gc.time                           avgt   10       29,000                   ms

BigJsonBenchmarkDeserialize.runCirce:·gc.alloc.rate.norm                        avgt   10   963560,566 ±      1,979    B/op
BigJsonBenchmarkDeserialize.runCirce:·gc.count                                  avgt   10       64,000               counts
BigJsonBenchmarkDeserialize.runCirce:·gc.time                                   avgt   10       31,000                   ms

BigJsonBenchmarkDeserialize.runSphereJson:·gc.alloc.rate.norm                   avgt   10   651872,467 ±      1,615    B/op
BigJsonBenchmarkDeserialize.runSphereJson:·gc.count                             avgt   10       52,000               counts
BigJsonBenchmarkDeserialize.runSphereJson:·gc.time                              avgt   10       28,000                   ms

BigJsonBenchmarkDeserialize.runSprayJson:·gc.alloc.rate.norm                    avgt   10   511920,611 ±      2,148    B/op
BigJsonBenchmarkDeserialize.runSprayJson:·gc.count                              avgt   10       32,000               counts
BigJsonBenchmarkDeserialize.runSprayJson:·gc.time                               avgt   10       14,000                   ms

BigJsonBenchmarkDeserialize.runUPickle:·gc.alloc.rate.norm                      avgt   10   475776,496 ±      1,710    B/op
BigJsonBenchmarkDeserialize.runUPickle:·gc.count                                avgt   10       36,000               counts
BigJsonBenchmarkDeserialize.runUPickle:·gc.time                                 avgt   10       13,000                   ms

BigJsonBenchmarkDeserialize.runWeePickle:·gc.alloc.rate.norm                    avgt   10   140032,336 ±      1,109    B/op
BigJsonBenchmarkDeserialize.runWeePickle:·gc.count                              avgt   10       16,000               counts
BigJsonBenchmarkDeserialize.runWeePickle:·gc.time                               avgt   10        6,000                   ms

BigJsonBenchmarkDeserialize.runJsoniter:·gc.alloc.rate.norm                     avgt   10   129072,116 ±      0,399    B/op
BigJsonBenchmarkDeserialize.runJsoniter:·gc.count                               avgt   10       41,000               counts
BigJsonBenchmarkDeserialize.runJsoniter:·gc.time                                avgt   10       16,000                   ms

BigJsonBenchmarkDeserialize.runJacksonParsing:·gc.alloc.rate.norm               avgt   10   123736,278 ±      0,959    B/op
BigJsonBenchmarkDeserialize.runJacksonParsing:·gc.count                         avgt   10       17,000               counts
BigJsonBenchmarkDeserialize.runJacksonParsing:·gc.time                          avgt   10        8,000                   ms

BigJsonBenchmarkDeserialize.runBorer:·gc.alloc.rate.norm                        avgt   10   121016,231 ±      0,798    B/op
BigJsonBenchmarkDeserialize.runBorer:·gc.count                                  avgt   10       19,000               counts
BigJsonBenchmarkDeserialize.runBorer:·gc.time                                   avgt   10        8,000                   ms
```
_ordered (lower is better)_

## Serialization (case class -> String):

```
Benchmark                                                                       Mode  Cnt        Score         Error   Units

BigJsonBenchmarkSerialize.runJson4sNative:·gc.alloc.rate.norm                   avgt   10  3819818,634 ±      8,981    B/op
BigJsonBenchmarkSerialize.runJson4sNative:·gc.count                             avgt   10       54,000               counts
BigJsonBenchmarkSerialize.runJson4sNative:·gc.time                              avgt   10       20,000                   ms

BigJsonBenchmarkSerialize.runJson4sJackson:·gc.alloc.rate.norm                  avgt   10  3209138,007 ±      6,898    B/op
BigJsonBenchmarkSerialize.runJson4sJackson:·gc.count                            avgt   10       59,000               counts
BigJsonBenchmarkSerialize.runJson4sJackson:·gc.time                             avgt   10       31,000                   ms

BigJsonBenchmarkSerialize.runPlayJson:·gc.alloc.rate.norm                       avgt   10  1984673,094 ±      3,772    B/op
BigJsonBenchmarkSerialize.runPlayJson:·gc.count                                 avgt   10       67,000               counts
BigJsonBenchmarkSerialize.runPlayJson:·gc.time                                  avgt   10       31,000                   ms

BigJsonBenchmarkSerialize.runArgonautJson:·gc.alloc.rate.norm                   avgt   10  1111728,639 ±      2,191    B/op
BigJsonBenchmarkSerialize.runArgonautJson:·gc.count                             avgt   10       64,000               counts
BigJsonBenchmarkSerialize.runArgonautJson:·gc.time                              avgt   10       36,000                   ms

BigJsonBenchmarkSerialize.runCirce:·gc.alloc.rate.norm                          avgt   10   795352,545 ±      1,868    B/op
BigJsonBenchmarkSerialize.runCirce:·gc.count                                    avgt   10       54,000               counts
BigJsonBenchmarkSerialize.runCirce:·gc.time                                     avgt   10       22,000                   ms

BigJsonBenchmarkSerialize.runRefuelParsing:·gc.alloc.rate.norm                  avgt   10   771001,073 ±      3,671    B/op
BigJsonBenchmarkSerialize.runRefuelParsing:·gc.count                            avgt   10       27,000               counts
BigJsonBenchmarkSerialize.runRefuelParsing:·gc.time                             avgt   10       15,000                   ms

BigJsonBenchmarkSerialize.runSprayJson:·gc.alloc.rate.norm                      avgt   10   643465,089 ±      3,232    B/op
BigJsonBenchmarkSerialize.runSprayJson:·gc.count                                avgt   10       52,000               counts
BigJsonBenchmarkSerialize.runSprayJson:·gc.time                                 avgt   10       21,000                   ms

BigJsonBenchmarkSerialize.runSphereJson:·gc.alloc.rate.norm                     avgt   10   438536,379 ±      1,333    B/op
BigJsonBenchmarkSerialize.runSphereJson:·gc.count                               avgt   10       48,000               counts
BigJsonBenchmarkSerialize.runSphereJson:·gc.time                                avgt   10       26,000                   ms

BigJsonBenchmarkSerialize.runUPickle:·gc.alloc.rate.norm                        avgt   10   158137,003 ±      3,448    B/op
BigJsonBenchmarkSerialize.runUPickle:·gc.count                                  avgt   10        6,000               counts
BigJsonBenchmarkSerialize.runUPickle:·gc.time                                   avgt   10        3,000                   ms

BigJsonBenchmarkSerialize.runWeePickle:·gc.alloc.rate.norm                      avgt   10   106712,194 ±      0,663    B/op
BigJsonBenchmarkSerialize.runWeePickle:·gc.count                                avgt   10       20,000               counts
BigJsonBenchmarkSerialize.runWeePickle:·gc.time                                 avgt   10        7,000                   ms

BigJsonBenchmarkSerialize.runJsoniter:·gc.alloc.rate.norm                       avgt   10    97576,116 ±      0,400    B/op
BigJsonBenchmarkSerialize.runJsoniter:·gc.count                                 avgt   10       31,000               counts
BigJsonBenchmarkSerialize.runJsoniter:·gc.time                                  avgt   10       11,000                   ms

BigJsonBenchmarkSerialize.runBorer:·gc.alloc.rate.norm                          avgt   10    96800,232 ±      0,805    B/op
BigJsonBenchmarkSerialize.runBorer:·gc.count                                    avgt   10       15,000               counts
BigJsonBenchmarkSerialize.runBorer:·gc.time                                     avgt   10        7,000                   ms

BigJsonBenchmarkSerialize.runJacksonParsing:·gc.alloc.rate.norm                 avgt   10    46408,133 ±      0,457    B/op
BigJsonBenchmarkSerialize.runJacksonParsing:·gc.count                           avgt   10       13,000               counts
BigJsonBenchmarkSerialize.runJacksonParsing:·gc.time                            avgt   10        8,000                   ms

```
_ordered (lower is better)_
