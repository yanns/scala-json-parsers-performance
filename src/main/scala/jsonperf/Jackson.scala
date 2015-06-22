package jsonperf

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

object Jackson {

  val mapper = new ObjectMapper()
  mapper.registerModule(DefaultScalaModule)
}
