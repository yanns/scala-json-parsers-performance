name := """json-perf"""

version := "1.0"

scalaVersion := "2.13.3"

val json4sVersion = "3.7.0-M7"
val circeVersion = "0.13.0"

resolvers ++=
  Resolver.bintrayRepo("commercetools", "maven") ::
  Resolver.bintrayRepo("rallyhealth", "maven")   ::
  Nil

libraryDependencies ++=
  "io.circe"                              %% "circe-generic"         % circeVersion  ::
  "io.circe"                              %% "circe-parser"          % circeVersion  ::
  "com.fasterxml.jackson.module"          %% "jackson-module-scala"  % "2.12.0"      ::
  "org.json4s"                            %% "json4s-native"         % json4sVersion ::
  "org.json4s"                            %% "json4s-jackson"        % json4sVersion ::
  "io.sphere"                             %% "sphere-json"           % "0.12.0"      ::
  "com.typesafe.play"                     %% "play-json"             % "2.9.1"       ::
  "io.spray"                              %% "spray-json"            % "1.3.6"       ::
  "io.argonaut"                           %% "argonaut"              % "6.3.2"       ::
  "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-macros" % "2.6.2"       ::
  "com.lihaoyi"                           %% "upickle"               % "1.2.2"       ::
  "com.phylage"                           %% "refuel-json"           % "1.4.2"       ::
  "io.bullet"                             %% "borer-derivation"      % "1.6.3"       ::
  "com.rallyhealth"                       %% "weepickle-v1"          % "1.3.1"       ::
  Nil

libraryDependencies ++=
  "org.scalatest"                         %% "scalatest"             % "3.2.3"       ::
  Nil map (_ % Test)

parallelExecution in Test := false

enablePlugins(JmhPlugin)
