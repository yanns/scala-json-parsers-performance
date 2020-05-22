name := """json-perf"""

version := "1.0"

scalaVersion := "2.12.11"

val json4sVersion = "3.7.0-M2"
val circeVersion = "0.13.0"

resolvers ++=
  Resolver.bintrayRepo("commercetools", "maven") ::
  Resolver.bintrayRepo("rallyhealth", "maven")   ::
  Nil

libraryDependencies ++=
  "io.circe"                              %% "circe-generic"         % circeVersion  ::
  "io.circe"                              %% "circe-parser"          % circeVersion  ::
  "com.fasterxml.jackson.module"          %% "jackson-module-scala"  % "2.10.3"      ::
  "org.json4s"                            %% "json4s-native"         % json4sVersion ::
  "org.json4s"                            %% "json4s-jackson"        % json4sVersion ::
  "io.sphere"                             %% "sphere-json"           % "0.11.2"      ::
  "com.typesafe.play"                     %% "play-json"             % "2.8.1"       ::
  "io.spray"                              %% "spray-json"            % "1.3.5"       ::
  "io.argonaut"                           %% "argonaut"              % "6.3.0"       ::
  "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-macros" % "2.1.7"       ::
  "com.lihaoyi"                           %% "upickle"               % "1.0.0"       ::
  "com.phylage"                           %% "refuel-json"           % "1.0.2"       ::
  "io.bullet"                             %% "borer-derivation"      % "1.6.0"       ::
  "com.rallyhealth"                       %% "weepickle-v1"          % "1.0.1"       ::
  Nil

libraryDependencies ++=
  "org.scalatest"                         %% "scalatest"             % "3.1.2"       ::
  Nil map (_ % Test)

parallelExecution in Test := false

enablePlugins(JmhPlugin)
