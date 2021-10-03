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
BigJsonBenchmarkDeserialize.runJson4sNative    avgt   10   0.947 ±  0.005  ms/op
BigJsonBenchmarkDeserialize.runJson4sJackson   avgt   10   0.814 ±  0.003  ms/op
BigJsonBenchmarkDeserialize.runArgonautJson    avgt   10   0.534 ±  0.007  ms/op
BigJsonBenchmarkDeserialize.runPlayJson        avgt   10   0.424 ±  0.007  ms/op
BigJsonBenchmarkDeserialize.runCirce           avgt   10   0.246 ±  0.002  ms/op
BigJsonBenchmarkDeserialize.runSprayJson       avgt   10   0.244 ±  0.002  ms/op
BigJsonBenchmarkDeserialize.runRefuelParsing   avgt   10   0.212 ±  0.004  ms/op
BigJsonBenchmarkDeserialize.runWeePickle       avgt   10   0.149 ±  0.001  ms/op
BigJsonBenchmarkDeserialize.runJacksonParsing  avgt   10   0.142 ±  0.001  ms/op
BigJsonBenchmarkDeserialize.runUPickle         avgt   10   0.138 ±  0.001  ms/op
BigJsonBenchmarkDeserialize.runBorer           avgt   10   0.104 ±  0.001  ms/op
BigJsonBenchmarkDeserialize.runJsoniter        avgt   10   0.057 ±  0.001  ms/op

```
_ordered (lower is better)_

## Serialization (case class -> String)

```
Benchmark                                      Mode  Cnt   Score    Error  Units
BigJsonBenchmarkSerialize.runJson4sNative      avgt   10   1.054 ±  0.006  ms/op
BigJsonBenchmarkSerialize.runJson4sJackson     avgt   10   0.842 ±  0.009  ms/op
BigJsonBenchmarkSerialize.runPlayJson          avgt   10   0.544 ±  0.033  ms/op
BigJsonBenchmarkSerialize.runArgonautJson      avgt   10   0.362 ±  0.001  ms/op
BigJsonBenchmarkSerialize.runRefuelParsing     avgt   10   0.273 ±  0.013  ms/op
BigJsonBenchmarkSerialize.runCirce             avgt   10   0.236 ±  0.002  ms/op
BigJsonBenchmarkSerialize.runSprayJson         avgt   10   0.235 ±  0.061  ms/op
BigJsonBenchmarkSerialize.runUPickle           avgt   10   0.144 ±  0.082  ms/op
BigJsonBenchmarkSerialize.runBorer             avgt   10   0.107 ±  0.001  ms/op
BigJsonBenchmarkSerialize.runWeePickle         avgt   10   0.103 ±  0.012  ms/op
BigJsonBenchmarkSerialize.runJacksonParsing    avgt   10   0.063 ±  0.001  ms/op
BigJsonBenchmarkSerialize.runJsoniter          avgt   10   0.043 ±  0.001  ms/op

```
_ordered (lower is better)_

# Pressure on the GC

## Run with

    jmh:run -i 10 -wi 10 -f1 -t1 -prof gc

## Deserialization (String -> case class)

```
Benchmark                                                                       Mode  Cnt        Score        Error   Units
BigJsonBenchmarkDeserialize.runJson4sNative:·gc.alloc.rate.norm                 avgt   10  2905399.030 ±     20.040    B/op
BigJsonBenchmarkDeserialize.runJson4sNative:·gc.count                           avgt   10       75.000               counts
BigJsonBenchmarkDeserialize.runJson4sNative:·gc.time                            avgt   10       60.000                   ms

BigJsonBenchmarkDeserialize.runArgonautJson:·gc.alloc.rate.norm                 avgt   10  2594740.879 ±      7.023    B/op
BigJsonBenchmarkDeserialize.runArgonautJson:·gc.count                           avgt   10      120.000               counts
BigJsonBenchmarkDeserialize.runArgonautJson:·gc.time                            avgt   10       85.000                   ms

BigJsonBenchmarkDeserialize.runJson4sJackson:·gc.alloc.rate.norm                avgt   10  2265822.738 ±     16.432    B/op
BigJsonBenchmarkDeserialize.runJson4sJackson:·gc.count                          avgt   10       68.000               counts
BigJsonBenchmarkDeserialize.runJson4sJackson:·gc.time                           avgt   10       56.000                   ms

BigJsonBenchmarkDeserialize.runPlayJson:·gc.alloc.rate.norm                     avgt   10  1979112.661 ±      3.806    B/op
BigJsonBenchmarkDeserialize.runPlayJson:·gc.count                               avgt   10      110.000               counts
BigJsonBenchmarkDeserialize.runPlayJson:·gc.time                                avgt   10       61.000                   ms

BigJsonBenchmarkDeserialize.runCirce:·gc.alloc.rate.norm                        avgt   10   853638.294 ±     14.586    B/op
BigJsonBenchmarkDeserialize.runCirce:·gc.count                                  avgt   10       87.000               counts
BigJsonBenchmarkDeserialize.runCirce:·gc.time                                   avgt   10       50.000                   ms

BigJsonBenchmarkDeserialize.runRefuelParsing:·gc.alloc.rate.norm                avgt   10   814906.205 ±      3.597    B/op
BigJsonBenchmarkDeserialize.runRefuelParsing:·gc.count                          avgt   10       96.000               counts
BigJsonBenchmarkDeserialize.runRefuelParsing:·gc.time                           avgt   10       51.000                   ms

BigJsonBenchmarkDeserialize.runSprayJson:·gc.alloc.rate.norm                    avgt   10   511952.774 ±      3.876    B/op
BigJsonBenchmarkDeserialize.runSprayJson:·gc.count                              avgt   10       51.000               counts
BigJsonBenchmarkDeserialize.runSprayJson:·gc.time                               avgt   10       28.000                   ms

BigJsonBenchmarkDeserialize.runUPickle:·gc.alloc.rate.norm                      avgt   10   445387.898 ±      2.917    B/op
BigJsonBenchmarkDeserialize.runUPickle:·gc.count                                avgt   10       81.000               counts
BigJsonBenchmarkDeserialize.runUPickle:·gc.time                                 avgt   10       37.000                   ms

BigJsonBenchmarkDeserialize.runWeePickle:·gc.alloc.rate.norm                    avgt   10   139977.371 ±      3.350    B/op
BigJsonBenchmarkDeserialize.runWeePickle:·gc.count                              avgt   10       23.000               counts
BigJsonBenchmarkDeserialize.runWeePickle:·gc.time                               avgt   10       11.000                   ms

BigJsonBenchmarkDeserialize.runJsoniter:·gc.alloc.rate.norm                     avgt   10   129080.402 ±      1.342    B/op
BigJsonBenchmarkDeserialize.runJsoniter:·gc.count                               avgt   10       55.000               counts
BigJsonBenchmarkDeserialize.runJsoniter:·gc.time                                avgt   10       26.000                   ms

BigJsonBenchmarkDeserialize.runJacksonParsing:·gc.alloc.rate.norm               avgt   10   123744.462 ±      2.522    B/op
BigJsonBenchmarkDeserialize.runJacksonParsing:·gc.count                         avgt   10       23.000               counts
BigJsonBenchmarkDeserialize.runJacksonParsing:·gc.time                          avgt   10       11.000                   ms

BigJsonBenchmarkDeserialize.runBorer:·gc.alloc.rate.norm                        avgt   10   121024.240 ±      0.962    B/op
BigJsonBenchmarkDeserialize.runBorer:·gc.count                                  avgt   10       28.000               counts
BigJsonBenchmarkDeserialize.runBorer:·gc.time                                   avgt   10       13.000                   ms
```
_ordered (lower is better)_

## Serialization (case class -> String):

```
Benchmark                                                                       Mode  Cnt        Score        Error   Units
BigJsonBenchmarkSerialize.runJson4sNative:·gc.alloc.rate.norm                   avgt   10  3344328.926 ±     19.563    B/op
BigJsonBenchmarkSerialize.runJson4sNative:·gc.count                             avgt   10       77.000               counts
BigJsonBenchmarkSerialize.runJson4sNative:·gc.time                              avgt   10       36.000                   ms

BigJsonBenchmarkSerialize.runJson4sJackson:·gc.alloc.rate.norm                  avgt   10  3153256.663 ±     19.661    B/op
BigJsonBenchmarkSerialize.runJson4sJackson:·gc.count                            avgt   10       84.000               counts
BigJsonBenchmarkSerialize.runJson4sJackson:·gc.time                             avgt   10       52.000                   ms

BigJsonBenchmarkSerialize.runPlayJson:·gc.alloc.rate.norm                       avgt   10  1968762.035 ±      6.548    B/op
BigJsonBenchmarkSerialize.runPlayJson:·gc.count                                 avgt   10       91.000               counts
BigJsonBenchmarkSerialize.runPlayJson:·gc.time                                  avgt   10       53.000                   ms

BigJsonBenchmarkSerialize.runArgonautJson:·gc.alloc.rate.norm                   avgt   10  1052931.268 ±      7.999    B/op
BigJsonBenchmarkSerialize.runArgonautJson:·gc.count                             avgt   10       59.000               counts
BigJsonBenchmarkSerialize.runArgonautJson:·gc.time                              avgt   10       48.000                   ms

BigJsonBenchmarkSerialize.runRefuelParsing:·gc.alloc.rate.norm                  avgt   10   869102.157 ±      2.253    B/op
BigJsonBenchmarkSerialize.runRefuelParsing:·gc.count                            avgt   10       80.000               counts
BigJsonBenchmarkSerialize.runRefuelParsing:·gc.time                             avgt   10       42.000                   ms

BigJsonBenchmarkSerialize.runCirce:·gc.alloc.rate.norm                          avgt   10   765495.965 ±      3.792    B/op
BigJsonBenchmarkSerialize.runCirce:·gc.count                                    avgt   10       82.000               counts
BigJsonBenchmarkSerialize.runCirce:·gc.time                                     avgt   10       45.000                   ms

BigJsonBenchmarkSerialize.runSprayJson:·gc.alloc.rate.norm                      avgt   10   643952.390 ±      4.875    B/op
BigJsonBenchmarkSerialize.runSprayJson:·gc.count                                avgt   10       70.000               counts
BigJsonBenchmarkSerialize.runSprayJson:·gc.time                                 avgt   10       34.000                   ms

BigJsonBenchmarkSerialize.runUPickle:·gc.alloc.rate.norm                        avgt   10   138161.263 ±      0.673    B/op
BigJsonBenchmarkSerialize.runUPickle:·gc.count                                  avgt   10       30.000               counts
BigJsonBenchmarkSerialize.runUPickle:·gc.time                                   avgt   10       13.000                   ms

BigJsonBenchmarkSerialize.runWeePickle:·gc.alloc.rate.norm                      avgt   10   106679.182 ±      1.740    B/op
BigJsonBenchmarkSerialize.runWeePickle:·gc.count                                avgt   10       28.000               counts
BigJsonBenchmarkSerialize.runWeePickle:·gc.time                                 avgt   10       13.000                   ms

BigJsonBenchmarkSerialize.runJsoniter:·gc.alloc.rate.norm                       avgt   10    97582.297 ±      0.779    B/op
BigJsonBenchmarkSerialize.runJsoniter:·gc.count                                 avgt   10       58.000               counts
BigJsonBenchmarkSerialize.runJsoniter:·gc.time                                  avgt   10       27.000                   ms

BigJsonBenchmarkSerialize.runBorer:·gc.alloc.rate.norm                          avgt   10    96806.365 ±      1.792    B/op
BigJsonBenchmarkSerialize.runBorer:·gc.count                                    avgt   10       22.000               counts
BigJsonBenchmarkSerialize.runBorer:·gc.time                                     avgt   10       11.000                   ms

BigJsonBenchmarkSerialize.runJacksonParsing:·gc.alloc.rate.norm                 avgt   10    46387.011 ±      1.115    B/op
BigJsonBenchmarkSerialize.runJacksonParsing:·gc.count                           avgt   10       17.000               counts
BigJsonBenchmarkSerialize.runJacksonParsing:·gc.time                            avgt   10        8.000                   ms
```
_ordered (lower is better)_
