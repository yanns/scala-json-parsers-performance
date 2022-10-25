name := """json-perf"""

version := "1.0"

scalaVersion := "2.13.10"

val json4sVersion = "4.0.3"
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
  "com.commercetools"                     %% "sphere-json"           % "0.12.5"      ::
  "com.typesafe.play"                     %% "play-json"             % "2.9.2"       ::
  "io.spray"                              %% "spray-json"            % "1.3.6"       ::
  "io.argonaut"                           %% "argonaut"              % "6.3.3"       ::
  "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-macros" % "2.8.1"       ::
  "com.lihaoyi"                           %% "upickle"               % "1.3.12"      ::
  "com.phylage"                           %% "refuel-json"           % "2.0.1"       ::
  "io.bullet"                             %% "borer-derivation"      % "1.7.2"       ::
  "com.rallyhealth"                       %% "weepickle-v1"          % "1.4.1"       ::
  Nil

libraryDependencies ++=
  "org.scalatest"                         %% "scalatest"             % "3.2.8"       ::
  Nil map (_ % Test)

parallelExecution in Test := false

enablePlugins(JmhPlugin)
