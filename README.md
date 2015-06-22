Scala JSON parser performance comparator
========================================

Compares performance of several JSON parsers used in Scala.
The test case is to deserialize a json into a case class.

# result
## with a little json:

    [info] Parameters(parser -> jackson): 0.057685
    [info] Parameters(parser -> json4sNative): 0.242923
    [info] Parameters(parser -> json4sJackson): 0.196812
    [info] Parameters(parser -> sphereJson): 0.11691
    [info] Parameters(parser -> playJson): 0.22276
    [info] Parameters(parser -> sprayJson): 0.191522
    [info] Parameters(parser -> argonaut): 0.151878

## with a big json:

    [info] Parameters(parser -> jackson): 2.622774
    [info] Parameters(parser -> json4sNative): 10.401216
    [info] Parameters(parser -> json4sJackson): 8.802245
    [info] Parameters(parser -> sphereJson): 5.616886
    [info] Parameters(parser -> playJson): 9.075994
    [info] Parameters(parser -> sprayJson): 3.006003
    [info] Parameters(parser -> argonaut): 1.666504