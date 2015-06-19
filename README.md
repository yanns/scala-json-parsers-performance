Scala JSON parser performance comparator
========================================

Compares performance of several JSON parsers used in Scala.
The test case is to deserialize a json into a case class.

# result
## with a little json:

    [info] Parameters(parser -> jackson):       0.063101
    [info] Parameters(parser -> json4sNative):  0.245529
    [info] Parameters(parser -> json4sJackson): 0.203579
    [info] Parameters(parser -> sphereJson):    0.119302
    [info] Parameters(parser -> playJson):      0.189492    


## with a big json:

    [info] Parameters(parser -> jackson):       3.272538
    [info] Parameters(parser -> json4sNative): 13.385458
    [info] Parameters(parser -> json4sJackson): 8.430015
    [info] Parameters(parser -> sphereJson):    6.980652
    [info] Parameters(parser -> playJson):      5.875176