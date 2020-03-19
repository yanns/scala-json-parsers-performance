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
- [refuel-josn](https://github.com/giiita/refuel)


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
BigJsonBenchmarkDeserialize.runJson4sNative    avgt   10   2.208 ±  1.543  ms/op
BigJsonBenchmarkDeserialize.runPlayJson        avgt   10   1.213 ±  0.043  ms/op
BigJsonBenchmarkDeserialize.runJson4sJackson   avgt   10   1.102 ±  0.061  ms/op
BigJsonBenchmarkDeserialize.runArgonautJson    avgt   10   0.849 ±  0.056  ms/op
BigJsonBenchmarkDeserialize.runRefuelParsing   avgt   10   0.552 ±  0.084  ms/op
BigJsonBenchmarkDeserialize.runUJson           avgt   10   0.369 ±  0.081  ms/op
BigJsonBenchmarkDeserialize.runSphereJson      avgt   10   0.355 ±  0.062  ms/op
BigJsonBenchmarkDeserialize.runCirce           avgt   10   0.341 ±  0.018  ms/op
BigJsonBenchmarkDeserialize.runSprayJson       avgt   10   0.304 ±  0.079  ms/op
BigJsonBenchmarkDeserialize.runJacksonParsing  avgt   10   0.176 ±  0.007  ms/op
BigJsonBenchmarkDeserialize.runJsoniter        avgt   10   0.093 ±  0.002  ms/op
```
_ordered (lower is better)_

## Serialization (case class -> String)

```
Benchmark                                      Mode  Cnt   Score    Error  Units
BigJsonBenchmarkSerialize.runJson4sJackson     avgt   10   1.137 ±  0.136  ms/op
BigJsonBenchmarkSerialize.runJson4sNative      avgt   10   0.850 ±  0.060  ms/op
BigJsonBenchmarkSerialize.runArgonautJson      avgt   10   0.792 ±  0.029  ms/op
BigJsonBenchmarkSerialize.runPlayJson          avgt   10   0.610 ±  0.029  ms/op
BigJsonBenchmarkSerialize.runCirce             avgt   10   0.466 ±  0.055  ms/op
BigJsonBenchmarkSerialize.runSprayJson         avgt   10   0.371 ±  0.046  ms/op
BigJsonBenchmarkSerialize.runRefuelParsing     avgt   10   0.263 ±  0.009  ms/op
BigJsonBenchmarkSerialize.runSphereJson        avgt   10   0.253 ±  0.003  ms/op
BigJsonBenchmarkSerialize.runUJson             avgt   10   0.238 ±  0.005  ms/op
BigJsonBenchmarkSerialize.runJacksonParsing    avgt   10   0.085 ±  0.007  ms/op
BigJsonBenchmarkSerialize.runJsoniter          avgt   10   0.058 ±  0.002  ms/op
```
_ordered (lower is better)_

# Pressure on the GC

## Run with

    jmh:run -i 10 -wi 10 -f1 -t1 -prof gc

## Deserialization (String -> case class)

```
Benchmark                                                                       Mode  Cnt        Score         Error   Units

BigJsonBenchmarkDeserialize.runPlayJson:·gc.alloc.rate.norm                     avgt   10  2951583.883 ±      16.612    B/op
BigJsonBenchmarkDeserialize.runPlayJson:·gc.count                               avgt   10       22.000                counts
BigJsonBenchmarkDeserialize.runPlayJson:·gc.time                                avgt   10       34.000                    ms

BigJsonBenchmarkDeserialize.runArgonautJson:·gc.alloc.rate.norm                 avgt   10  2930649.654 ±       5.925    B/op
BigJsonBenchmarkDeserialize.runArgonautJson:·gc.count                           avgt   10       26.000                counts
BigJsonBenchmarkDeserialize.runArgonautJson:·gc.time                            avgt   10       31.000                    ms

BigJsonBenchmarkDeserialize.runJson4sNative:·gc.alloc.rate.norm                 avgt   10  2409386.562 ±      50.181    B/op
BigJsonBenchmarkDeserialize.runJson4sNative:·gc.count                           avgt   10       18.000                counts
BigJsonBenchmarkDeserialize.runJson4sNative:·gc.time                            avgt   10       31.000                    ms

BigJsonBenchmarkDeserialize.runJson4sJackson:·gc.alloc.rate.norm                avgt   10  1633036.995 ±      38.855    B/op
BigJsonBenchmarkDeserialize.runJson4sJackson:·gc.count                          avgt   10       20.000                counts
BigJsonBenchmarkDeserialize.runJson4sJackson:·gc.time                           avgt   10       24.000                    ms

BigJsonBenchmarkDeserialize.runRefuelParsing:·gc.alloc.rate.norm                avgt   10  1247368.809 ±       2.765    B/op
BigJsonBenchmarkDeserialize.runRefuelParsing:·gc.count                          avgt   10       25.000                counts
BigJsonBenchmarkDeserialize.runRefuelParsing:·gc.time                           avgt   10       29.000                    ms

BigJsonBenchmarkDeserialize.runCirce:·gc.alloc.rate.norm                        avgt   10  1123968.649 ±       2.284    B/op
BigJsonBenchmarkDeserialize.runCirce:·gc.count                                  avgt   10       26.000                counts
BigJsonBenchmarkDeserialize.runCirce:·gc.time                                   avgt   10       36.000                    ms

BigJsonBenchmarkDeserialize.runSphereJson:·gc.alloc.rate.norm                   avgt   10   707640.574 ±       2.002    B/op
BigJsonBenchmarkDeserialize.runSphereJson:·gc.count                             avgt   10       23.000                counts
BigJsonBenchmarkDeserialize.runSphereJson:·gc.time                              avgt   10       23.000                    ms

BigJsonBenchmarkDeserialize.runUJson:·gc.alloc.rate.norm                        avgt   10   654648.403 ±       1.875    B/op
BigJsonBenchmarkDeserialize.runUJson:·gc.count                                  avgt   10       24.000                counts
BigJsonBenchmarkDeserialize.runUJson:·gc.time                                   avgt   10       23.000                    ms

BigJsonBenchmarkDeserialize.runSprayJson:·gc.alloc.rate.norm                    avgt   10   404900.000 ±       9.889    B/op
BigJsonBenchmarkDeserialize.runSprayJson:·gc.count                              avgt   10       19.000                counts
BigJsonBenchmarkDeserialize.runSprayJson:·gc.time                               avgt   10       17.000                    ms

BigJsonBenchmarkDeserialize.runJsoniter:·gc.alloc.rate.norm                     avgt   10   236224.039 ±       1.037    B/op
BigJsonBenchmarkDeserialize.runJsoniter:·gc.count                               avgt   10       26.000                counts
BigJsonBenchmarkDeserialize.runJsoniter:·gc.time                                avgt   10       24.000                    ms

BigJsonBenchmarkDeserialize.runJacksonParsing:·gc.alloc.rate.norm               avgt   10   131520.386 ±       1.141    B/op
BigJsonBenchmarkDeserialize.runJacksonParsing:·gc.count                         avgt   10       15.000                counts
BigJsonBenchmarkDeserialize.runJacksonParsing:·gc.time                          avgt   10       25.000                    ms
```
_ordered (lower is better)_

## Serialization (case class -> String):

```
Benchmark                                                                       Mode  Cnt        Score         Error   Units

BigJsonBenchmarkSerialize.runArgonautJson:·gc.alloc.rate.norm                   avgt   10  2161881.422 ±       4.922    B/op
BigJsonBenchmarkSerialize.runArgonautJson:·gc.count                             avgt   10       23.000                counts
BigJsonBenchmarkSerialize.runArgonautJson:·gc.time                              avgt   10       23.000                    ms

BigJsonBenchmarkSerialize.runPlayJson:·gc.alloc.rate.norm                       avgt   10  1722769.035 ±       3.644    B/op
BigJsonBenchmarkSerialize.runPlayJson:·gc.count                                 avgt   10       35.000                counts
BigJsonBenchmarkSerialize.runPlayJson:·gc.time                                  avgt   10       31.000                    ms

BigJsonBenchmarkSerialize.runJson4sNative:·gc.alloc.rate.norm                   avgt   10  1629193.510 ±       5.375    B/op
BigJsonBenchmarkSerialize.runJson4sNative:·gc.count                             avgt   10       25.000                counts
BigJsonBenchmarkSerialize.runJson4sNative:·gc.time                              avgt   10       22.000                    ms

BigJsonBenchmarkSerialize.runJson4sJackson:·gc.alloc.rate.norm                  avgt   10  1224969.549 ±       5.467    B/op
BigJsonBenchmarkSerialize.runJson4sJackson:·gc.count                            avgt   10       24.000                counts
BigJsonBenchmarkSerialize.runJson4sJackson:·gc.time                             avgt   10       23.000                    ms

BigJsonBenchmarkSerialize.runCirce:·gc.alloc.rate.norm                          avgt   10  1164896.750 ±       2.612    B/op
BigJsonBenchmarkSerialize.runCirce:·gc.count                                    avgt   10       27.000                counts
BigJsonBenchmarkSerialize.runCirce:·gc.time                                     avgt   10       25.000                    ms

BigJsonBenchmarkSerialize.runRefuelParsing:·gc.alloc.rate.norm                  avgt   10   732641.996 ±       7.268    B/op
BigJsonBenchmarkSerialize.runRefuelParsing:·gc.count                            avgt   10       37.000                counts
BigJsonBenchmarkSerialize.runRefuelParsing:·gc.time                             avgt   10       29.000                    ms

BigJsonBenchmarkSerialize.runSprayJson:·gc.alloc.rate.norm                      avgt   10   717384.570 ±       2.003    B/op
BigJsonBenchmarkSerialize.runSprayJson:·gc.count                                avgt   10       32.000                counts
BigJsonBenchmarkSerialize.runSprayJson:·gc.time                                 avgt   10       26.000                    ms

BigJsonBenchmarkSerialize.runSphereJson:·gc.alloc.rate.norm                     avgt   10   590440.472 ±       1.489    B/op
BigJsonBenchmarkSerialize.runSphereJson:·gc.count                               avgt   10       35.000                counts
BigJsonBenchmarkSerialize.runSphereJson:·gc.time                                avgt   10       28.000                    ms

BigJsonBenchmarkSerialize.runUJson:·gc.alloc.rate.norm                          avgt   10   327577.215 ±      18.012    B/op
BigJsonBenchmarkSerialize.runUJson:·gc.count                                    avgt   10       27.000                counts
BigJsonBenchmarkSerialize.runUJson:·gc.time                                     avgt   10       23.000                    ms

BigJsonBenchmarkSerialize.runJsoniter:·gc.alloc.rate.norm                       avgt   10   144672.210 ±       0.830    B/op
BigJsonBenchmarkSerialize.runJsoniter:·gc.count                                 avgt   10       29.000                counts
BigJsonBenchmarkSerialize.runJsoniter:·gc.time                                  avgt   10       26.000                    ms

BigJsonBenchmarkSerialize.runJacksonParsing:·gc.alloc.rate.norm                 avgt   10    80232.130 ±       0.443    B/op
BigJsonBenchmarkSerialize.runJacksonParsing:·gc.count                           avgt   10       17.000                counts
BigJsonBenchmarkSerialize.runJacksonParsing:·gc.time                            avgt   10       19.000                    ms
```
_ordered (lower is better)_
