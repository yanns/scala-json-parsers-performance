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
BigJsonBenchmarkDeserialize.runJson4sJackson   avgt   10   0.810 ±  0.010  ms/op
BigJsonBenchmarkDeserialize.runArgonautJson    avgt   10   0.523 ±  0.007  ms/op
BigJsonBenchmarkDeserialize.runPlayJson        avgt   10   0.456 ±  0.067  ms/op
BigJsonBenchmarkDeserialize.runCirce           avgt   10   0.256 ±  0.029  ms/op
BigJsonBenchmarkDeserialize.runSprayJson       avgt   10   0.237 ±  0.021  ms/op
BigJsonBenchmarkDeserialize.runRefuelParsing   avgt   10   0.219 ±  0.002  ms/op
BigJsonBenchmarkDeserialize.runSphereJson      avgt   10   0.167 ±  0.001  ms/op
BigJsonBenchmarkDeserialize.runWeePickle       avgt   10   0.151 ±  0.001  ms/op
BigJsonBenchmarkDeserialize.runUPickle         avgt   10   0.141 ±  0.001  ms/op
BigJsonBenchmarkDeserialize.runJacksonParsing  avgt   10   0.138 ±  0.001  ms/op
BigJsonBenchmarkDeserialize.runBorer           avgt   10   0.105 ±  0.001  ms/op
BigJsonBenchmarkDeserialize.runJsoniter        avgt   10   0.060 ±  0.007  ms/op
```
_ordered (lower is better)_

## Serialization (case class -> String)

```
Benchmark                                      Mode  Cnt   Score    Error  Units
BigJsonBenchmarkSerialize.runJson4sJackson     avgt   10   0.847 ±  0.007  ms/op
BigJsonBenchmarkSerialize.runPlayJson          avgt   10   0.531 ±  0.003  ms/op
BigJsonBenchmarkSerialize.runArgonautJson      avgt   10   0.335 ±  0.030  ms/op
BigJsonBenchmarkSerialize.runRefuelParsing     avgt   10   0.271 ±  0.033  ms/op
BigJsonBenchmarkSerialize.runCirce             avgt   10   0.237 ±  0.002  ms/op
BigJsonBenchmarkSerialize.runSprayJson         avgt   10   0.215 ±  0.001  ms/op
BigJsonBenchmarkSerialize.runBorer             avgt   10   0.103 ±  0.001  ms/op
BigJsonBenchmarkSerialize.runUPickle           avgt   10   0.098 ±  0.008  ms/op
BigJsonBenchmarkSerialize.runWeePickle         avgt   10   0.098 ±  0.001  ms/op
BigJsonBenchmarkSerialize.runSphereJson        avgt   10   0.091 ±  0.001  ms/op
BigJsonBenchmarkSerialize.runJacksonParsing    avgt   10   0.063 ±  0.004  ms/op
BigJsonBenchmarkSerialize.runJsoniter          avgt   10   0.042 ±  0.001  ms/op

```
_ordered (lower is better)_

# Pressure on the GC

## Run with

    jmh:run -i 10 -wi 10 -f1 -t1 -prof gc

## Deserialization (String -> case class)

```
Benchmark                                                                       Mode  Cnt        Score        Error   Units
BigJsonBenchmarkDeserialize.runArgonautJson:·gc.alloc.rate.norm                 avgt   10  2578769.986 ±     53.779    B/op
BigJsonBenchmarkDeserialize.runArgonautJson:·gc.count                           avgt   10       96.000               counts
BigJsonBenchmarkDeserialize.runArgonautJson:·gc.time                            avgt   10       63.000                   ms

BigJsonBenchmarkDeserialize.runJson4sJackson:·gc.alloc.rate.norm                avgt   10  2065771.154 ±     15.221    B/op
BigJsonBenchmarkDeserialize.runJson4sJackson:·gc.count                          avgt   10       62.000               counts
BigJsonBenchmarkDeserialize.runJson4sJackson:·gc.time                           avgt   10       47.000                   ms

BigJsonBenchmarkDeserialize.runPlayJson:·gc.alloc.rate.norm                     avgt   10  1979111.057 ±      8.216    B/op
BigJsonBenchmarkDeserialize.runPlayJson:·gc.count                               avgt   10      107.000               counts
BigJsonBenchmarkDeserialize.runPlayJson:·gc.time                                avgt   10       53.000                   ms

BigJsonBenchmarkDeserialize.runCirce:·gc.alloc.rate.norm                        avgt   10   867614.524 ±      9.233    B/op
BigJsonBenchmarkDeserialize.runCirce:·gc.count                                  avgt   10       86.000               counts
BigJsonBenchmarkDeserialize.runCirce:·gc.time                                   avgt   10       47.000                   ms

BigJsonBenchmarkDeserialize.runRefuelParsing:·gc.alloc.rate.norm                avgt   10   814906.798 ±      3.588    B/op
BigJsonBenchmarkDeserialize.runRefuelParsing:·gc.count                          avgt   10       92.000               counts
BigJsonBenchmarkDeserialize.runRefuelParsing:·gc.time                           avgt   10       44.000                   ms

BigJsonBenchmarkDeserialize.runSprayJson:·gc.alloc.rate.norm                    avgt   10   511955.300 ±      5.591    B/op
BigJsonBenchmarkDeserialize.runSprayJson:·gc.count                              avgt   10       51.000               counts
BigJsonBenchmarkDeserialize.runSprayJson:·gc.time                               avgt   10       27.000                   ms

BigJsonBenchmarkDeserialize.runUPickle:·gc.alloc.rate.norm                      avgt   10   413386.161 ±      2.526    B/op
BigJsonBenchmarkDeserialize.runUPickle:·gc.count                                avgt   10       70.000               counts
BigJsonBenchmarkDeserialize.runUPickle:·gc.time                                 avgt   10       31.000                   ms

BigJsonBenchmarkDeserialize.runSphereJson:·gc.alloc.rate.norm                   avgt   10   371895.585 ±      3.512    B/op
BigJsonBenchmarkDeserialize.runSphereJson:·gc.count                             avgt   10       54.000               counts
BigJsonBenchmarkDeserialize.runSphereJson:·gc.time                              avgt   10       34.000                   ms

BigJsonBenchmarkDeserialize.runWeePickle:·gc.alloc.rate.norm                    avgt   10   139977.664 ±      3.377    B/op
BigJsonBenchmarkDeserialize.runWeePickle:·gc.count                              avgt   10       23.000               counts
BigJsonBenchmarkDeserialize.runWeePickle:·gc.time                               avgt   10       10.000                   ms

BigJsonBenchmarkDeserialize.runJsoniter:·gc.alloc.rate.norm                     avgt   10   129080.363 ±      1.384    B/op
BigJsonBenchmarkDeserialize.runJsoniter:·gc.count                               avgt   10       53.000               counts
BigJsonBenchmarkDeserialize.runJsoniter:·gc.time                                avgt   10       23.000                   ms

BigJsonBenchmarkDeserialize.runJacksonParsing:·gc.alloc.rate.norm               avgt   10   123744.709 ±      2.402    B/op
BigJsonBenchmarkDeserialize.runJacksonParsing:·gc.count                         avgt   10       23.000               counts
BigJsonBenchmarkDeserialize.runJacksonParsing:·gc.time                          avgt   10       11.000                   ms

BigJsonBenchmarkDeserialize.runBorer:·gc.alloc.rate.norm                        avgt   10   121024.180 ±      2.153    B/op
BigJsonBenchmarkDeserialize.runBorer:·gc.count                                  avgt   10       28.000               counts
BigJsonBenchmarkDeserialize.runBorer:·gc.time                                   avgt   10       12.000                   ms
```
_ordered (lower is better)_

## Serialization (case class -> String):

```
Benchmark                                                                       Mode  Cnt        Score        Error   Units
BigJsonBenchmarkSerialize.runJson4sJackson:·gc.alloc.rate.norm                  avgt   10  3153258.449 ±     14.184    B/op
BigJsonBenchmarkSerialize.runJson4sJackson:·gc.count                            avgt   10       91.000               counts
BigJsonBenchmarkSerialize.runJson4sJackson:·gc.time                             avgt   10       53.000                   ms

BigJsonBenchmarkSerialize.runPlayJson:·gc.alloc.rate.norm                       avgt   10  1968760.583 ±      5.757    B/op
BigJsonBenchmarkSerialize.runPlayJson:·gc.count                                 avgt   10       89.000               counts
BigJsonBenchmarkSerialize.runPlayJson:·gc.time                                  avgt   10       50.000                   ms

BigJsonBenchmarkSerialize.runArgonautJson:·gc.alloc.rate.norm                   avgt   10  1076875.212 ±      5.822    B/op
BigJsonBenchmarkSerialize.runArgonautJson:·gc.count                             avgt   10       88.000               counts
BigJsonBenchmarkSerialize.runArgonautJson:·gc.time                              avgt   10       61.000                   ms

BigJsonBenchmarkSerialize.runRefuelParsing:·gc.alloc.rate.norm                  avgt   10   893111.629 ±     22.419    B/op
BigJsonBenchmarkSerialize.runRefuelParsing:·gc.count                            avgt   10       80.000               counts
BigJsonBenchmarkSerialize.runRefuelParsing:·gc.time                             avgt   10       44.000                   ms

BigJsonBenchmarkSerialize.runCirce:·gc.alloc.rate.norm                          avgt   10   765495.574 ±      3.241    B/op
BigJsonBenchmarkSerialize.runCirce:·gc.count                                    avgt   10       79.000               counts
BigJsonBenchmarkSerialize.runCirce:·gc.time                                     avgt   10       39.000                   ms

BigJsonBenchmarkSerialize.runSprayJson:·gc.alloc.rate.norm                      avgt   10   643952.034 ±      4.426    B/op
BigJsonBenchmarkSerialize.runSprayJson:·gc.count                                avgt   10       73.000               counts
BigJsonBenchmarkSerialize.runSprayJson:·gc.time                                 avgt   10       36.000                   ms

BigJsonBenchmarkSerialize.runSphereJson:·gc.alloc.rate.norm                     avgt   10   222518.314 ±      1.144    B/op
BigJsonBenchmarkSerialize.runSphereJson:·gc.count                               avgt   10       60.000               counts
BigJsonBenchmarkSerialize.runSphereJson:·gc.time                                avgt   10       35.000                   ms

BigJsonBenchmarkSerialize.runUPickle:·gc.alloc.rate.norm                        avgt   10   138161.427 ±      2.101    B/op
BigJsonBenchmarkSerialize.runUPickle:·gc.count                                  avgt   10       35.000               counts
BigJsonBenchmarkSerialize.runUPickle:·gc.time                                   avgt   10       14.000                   ms

BigJsonBenchmarkSerialize.runWeePickle:·gc.alloc.rate.norm                      avgt   10   106655.199 ±      1.394    B/op
BigJsonBenchmarkSerialize.runWeePickle:·gc.count                                avgt   10       30.000               counts
BigJsonBenchmarkSerialize.runWeePickle:·gc.time                                 avgt   10       13.000                   ms

BigJsonBenchmarkSerialize.runJsoniter:·gc.alloc.rate.norm                       avgt   10    97582.325 ±      0.290    B/op
BigJsonBenchmarkSerialize.runJsoniter:·gc.count                                 avgt   10       60.000               counts
BigJsonBenchmarkSerialize.runJsoniter:·gc.time                                  avgt   10       26.000                   ms

BigJsonBenchmarkSerialize.runBorer:·gc.alloc.rate.norm                          avgt   10    96806.575 ±      2.005    B/op
BigJsonBenchmarkSerialize.runBorer:·gc.count                                    avgt   10       23.000               counts
BigJsonBenchmarkSerialize.runBorer:·gc.time                                     avgt   10       10.000                   ms

BigJsonBenchmarkSerialize.runJacksonParsing:·gc.alloc.rate.norm                 avgt   10    46387.296 ±      0.916    B/op
BigJsonBenchmarkSerialize.runJacksonParsing:·gc.count                           avgt   10       19.000               counts
BigJsonBenchmarkSerialize.runJacksonParsing:·gc.time                            avgt   10        9.000                   ms
```
_ordered (lower is better)_
