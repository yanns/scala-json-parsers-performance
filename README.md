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
[info] BigJsonBenchmarkDeserialize.runJson4sJackson   avgt    2   1,713          ms/op
[info] BigJsonBenchmarkDeserialize.runJson4sNative    avgt    2   1,747          ms/op
[info] BigJsonBenchmarkDeserialize.runPlayJson        avgt    2   1,558          ms/op
[info] BigJsonBenchmarkDeserialize.runArgonautJson    avgt    2   1,065          ms/op
[info] BigJsonBenchmarkDeserialize.runSphereJson      avgt    2   0,556          ms/op
[info] BigJsonBenchmarkDeserialize.runCirce           avgt    2   0,476          ms/op
[info] BigJsonBenchmarkDeserialize.runSprayJson       avgt    2   0,409          ms/op
[info] BigJsonBenchmarkDeserialize.runJacksonParsing  avgt    2   0,263          ms/op
```
_ordered (lower is better)_

## Serialization (case class -> String)

```
[info] Benchmark                                      Mode  Cnt   Score   Error  Units
[info] BigJsonBenchmarkSerialize.runJson4sNative      avgt    2   1,311          ms/op
[info] BigJsonBenchmarkSerialize.runJson4sJackson     avgt    2   1,161          ms/op
[info] BigJsonBenchmarkSerialize.runArgonautJson      avgt    2   1,149          ms/op
[info] BigJsonBenchmarkSerialize.runPlayJson          avgt    2   0,701          ms/op
[info] BigJsonBenchmarkSerialize.runCirce             avgt    2   0,561          ms/op
[info] BigJsonBenchmarkSerialize.runSprayJson         avgt    2   0,448          ms/op
[info] BigJsonBenchmarkSerialize.runSphereJson        avgt    2   0,321          ms/op
[info] BigJsonBenchmarkSerialize.runJacksonParsing    avgt    2   0,105          ms/op
```
_ordered (lower is better)_

# Pressure on the GC

## Run with

    jmh:run -i 10 -wi 10 -f1 -t1 -prof gc

## Deserialization (String -> case class)

```
[info] Benchmark                                                                                      Mode  Cnt        Score        Error   Units
[info] BigJsonBenchmarkDeserialize.runArgonautJson:·gc.alloc.rate.norm                                avgt   10  2859249,582 ±      5,490    B/op
[info] BigJsonBenchmarkDeserialize.runArgonautJson:·gc.count                                          avgt   10      120,000               counts
[info] BigJsonBenchmarkDeserialize.runArgonautJson:·gc.time                                           avgt   10      100,000                   ms

[info] BigJsonBenchmarkDeserialize.runCirce:·gc.alloc.rate.norm                                       avgt   10  1437025,005 ±      3,418    B/op
[info] BigJsonBenchmarkDeserialize.runCirce:·gc.count                                                 avgt   10      129,000               counts
[info] BigJsonBenchmarkDeserialize.runCirce:·gc.time                                                  avgt   10       92,000                   ms

[info] BigJsonBenchmarkDeserialize.runJacksonParsing:·gc.alloc.rate.norm                              avgt   10   123528,702 ±     12,790    B/op
[info] BigJsonBenchmarkDeserialize.runJacksonParsing:·gc.count                                        avgt   10       28,000               counts
[info] BigJsonBenchmarkDeserialize.runJacksonParsing:·gc.time                                         avgt   10       20,000                   ms

[info] BigJsonBenchmarkDeserialize.runJson4sJackson:·gc.alloc.rate.norm                               avgt   10  2145424,671 ±     92,383    B/op
[info] BigJsonBenchmarkDeserialize.runJson4sJackson:·gc.count                                         avgt   10       79,000               counts
[info] BigJsonBenchmarkDeserialize.runJson4sJackson:·gc.time                                          avgt   10       72,000                   ms

[info] BigJsonBenchmarkDeserialize.runJson4sNative:·gc.alloc.rate.norm                                avgt   10  2417394,260 ±     49,153    B/op
[info] BigJsonBenchmarkDeserialize.runJson4sNative:·gc.count                                          avgt   10       92,000               counts
[info] BigJsonBenchmarkDeserialize.runJson4sNative:·gc.time                                           avgt   10       85,000                   ms

[info] BigJsonBenchmarkDeserialize.runPlayJson:·gc.alloc.rate.norm                                    avgt   10  3080915,701 ±     24,501    B/op
[info] BigJsonBenchmarkDeserialize.runPlayJson:·gc.count                                              avgt   10      106,000               counts
[info] BigJsonBenchmarkDeserialize.runPlayJson:·gc.time                                               avgt   10       83,000                   ms

[info] BigJsonBenchmarkDeserialize.runSphereJson:·gc.alloc.rate.norm                                  avgt   10  1117104,923 ±      3,261    B/op
[info] BigJsonBenchmarkDeserialize.runSphereJson:·gc.count                                            avgt   10       82,000               counts
[info] BigJsonBenchmarkDeserialize.runSphereJson:·gc.time                                             avgt   10       68,000                   ms

[info] BigJsonBenchmarkDeserialize.runSprayJson:·gc.alloc.rate.norm                                   avgt   10   403672,579 ±      1,971    B/op
[info] BigJsonBenchmarkDeserialize.runSprayJson:·gc.count                                             avgt   10       63,000               counts
[info] BigJsonBenchmarkDeserialize.runSprayJson:·gc.time                                              avgt   10       40,000                   ms
```
_not ordered (lower is better)_

## Serialization (case class -> String):

```
[info] Benchmark                                                                                      Mode  Cnt        Score        Error   Units
[info] BigJsonBenchmarkSerialize.runArgonautJson:·gc.alloc.rate.norm                                  avgt   10  1347961,213 ±      3,708    B/op
[info] BigJsonBenchmarkSerialize.runArgonautJson:·gc.count                                            avgt   10      112,000               counts
[info] BigJsonBenchmarkSerialize.runArgonautJson:·gc.time                                             avgt   10       87,000                   ms

[info] BigJsonBenchmarkSerialize.runCirce:·gc.alloc.rate.norm                                         avgt   10  1444512,984 ±      3,368    B/op
[info] BigJsonBenchmarkSerialize.runCirce:·gc.count                                                   avgt   10      134,000               counts
[info] BigJsonBenchmarkSerialize.runCirce:·gc.time                                                    avgt   10       81,000                   ms

[info] BigJsonBenchmarkSerialize.runJacksonParsing:·gc.alloc.rate.norm                                avgt   10    46439,981 ±      0,977    B/op
[info] BigJsonBenchmarkSerialize.runJacksonParsing:·gc.count                                          avgt   10       29,000               counts
[info] BigJsonBenchmarkSerialize.runJacksonParsing:·gc.time                                           avgt   10       19,000                   ms

[info] BigJsonBenchmarkSerialize.runJson4sJackson:·gc.alloc.rate.norm                                 avgt   10  1351249,699 ±      5,749    B/op
[info] BigJsonBenchmarkSerialize.runJson4sJackson:·gc.count                                           avgt   10       73,000               counts
[info] BigJsonBenchmarkSerialize.runJson4sJackson:·gc.time                                            avgt   10       60,000                   ms

[info] BigJsonBenchmarkSerialize.runJson4sNative:·gc.alloc.rate.norm                                  avgt   10  1983353,700 ±      5,852    B/op
[info] BigJsonBenchmarkSerialize.runJson4sNative:·gc.count                                            avgt   10      107,000               counts
[info] BigJsonBenchmarkSerialize.runJson4sNative:·gc.time                                             avgt   10       63,000                   ms

[info] BigJsonBenchmarkSerialize.runPlayJson:·gc.alloc.rate.norm                                      avgt   10  1529761,038 ±      3,562    B/op
[info] BigJsonBenchmarkSerialize.runPlayJson:·gc.count                                                avgt   10       93,000               counts
[info] BigJsonBenchmarkSerialize.runPlayJson:·gc.time                                                 avgt   10       67,000                   ms

[info] BigJsonBenchmarkSerialize.runSphereJson:·gc.alloc.rate.norm                                    avgt   10   543728,475 ±      1,506    B/op
[info] BigJsonBenchmarkSerialize.runSphereJson:·gc.count                                              avgt   10       94,000               counts
[info] BigJsonBenchmarkSerialize.runSphereJson:·gc.time                                               avgt   10       81,000                   ms

[info] BigJsonBenchmarkSerialize.runSprayJson:·gc.alloc.rate.norm                                     avgt   10   626672,730 ±      2,533    B/op
[info] BigJsonBenchmarkSerialize.runSprayJson:·gc.count                                               avgt   10       83,000               counts
[info] BigJsonBenchmarkSerialize.runSprayJson:·gc.time                                                avgt   10       49,000                   ms
```
_not ordered (lower is better)_
