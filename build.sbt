name := """json-perf"""

version := "1.0"

scalaVersion := "2.13.5"

val json4sVersion = "3.7.0-M11"
val circeVersion = "0.13.0"

resolvers ++=
  Resolver.bintrayRepo("commercetools", "maven") ::
  Resolver.bintrayRepo("rallyhealth", "maven")   ::
  Nil

libraryDependencies ++=
  "io.circe"                              %% "circe-generic"         % circeVersion  ::
  "io.circe"                              %% "circe-parser"          % circeVersion  ::
  "com.fasterxml.jackson.module"          %% "jackson-module-scala"  % "2.12.3"      ::
  "org.json4s"                            %% "json4s-native"         % json4sVersion ::
  "org.json4s"                            %% "json4s-jackson"        % json4sVersion ::
  "io.sphere"                             %% "sphere-json"           % "0.12.1"      ::
  "com.typesafe.play"                     %% "play-json"             % "2.9.2"       ::
  "io.spray"                              %% "spray-json"            % "1.3.6"       ::
  "io.argonaut"                           %% "argonaut"              % "6.3.3"       ::
  "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-macros" % "2.7.3"       ::
  "com.lihaoyi"                           %% "upickle"               % "1.3.11"       ::
  "com.phylage"                           %% "refuel-json"           % "1.4.10"       ::
  "io.bullet"                             %% "borer-derivation"      % "1.7.0"       ::
  "com.rallyhealth"                       %% "weepickle-v1"          % "1.4.0"       ::
  Nil

libraryDependencies ++=
  "org.scalatest"                         %% "scalatest"             % "3.2.8"       ::
  Nil map (_ % Test)

parallelExecution in Test := false

enablePlugins(JmhPlugin)
