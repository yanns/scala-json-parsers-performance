Scala JSON parser performance comparator
========================================

Compares performance of several JSON parsers used in Scala.
The test case is to deserialize a json into a case class.

# result
## with a little json:

    [info] Parameters(parser -> jackson): 0.057743
    [info] Parameters(parser -> json4sNative): 0.254013
    [info] Parameters(parser -> json4sJackson): 0.206059
    [info] Parameters(parser -> sphereJson): 0.119205
    [info] Parameters(parser -> playJson): 0.236163
    [info] Parameters(parser -> sprayJson): 0.205972
    [info] Parameters(parser -> argonaut): 0.164939

## with a big json:

    [info] Parameters(parser -> jackson): 2.731169
    [info] Parameters(parser -> json4sNative): 11.741793
    [info] Parameters(parser -> json4sJackson): 9.111961
    [info] Parameters(parser -> sphereJson): 6.242644
    [info] Parameters(parser -> playJson): 11.189528
    [info] Parameters(parser -> sprayJson): 2.650359
    [info] Parameters(parser -> argonaut): 5.364636