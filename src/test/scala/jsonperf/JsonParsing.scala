package jsonperf

trait JsonParsing[A] extends (String ⇒ A) with Serializable