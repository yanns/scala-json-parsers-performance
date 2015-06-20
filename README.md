Scala JSON parser performance comparator
========================================

Compares performance of several JSON parsers used in Scala.
The test case is to deserialize a json into a case class.

# result
## with a little json:

    [info] Parameters(parser -> jackson): 0.057963
    [info] Parameters(parser -> json4sNative): 0.241968
    [info] Parameters(parser -> json4sJackson): 0.20111
    [info] Parameters(parser -> sphereJson): 0.12227
    [info] Parameters(parser -> playJson): 0.268749
    [info] Parameters(parser -> sprayJson): 0.12119

## with a big json:

    [info] Parameters(parser -> jackson): 2.687211
    [info] Parameters(parser -> json4sNative): 11.17669
    [info] Parameters(parser -> json4sJackson): 7.982636
    [info] Parameters(parser -> sphereJson): 6.249608
    [info] Parameters(parser -> playJson): 9.210661
    [info] Parameters(parser -> sprayJson): 1.873145