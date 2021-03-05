name := """json-perf"""

version := "1.0"

scalaVersion := "2.13.5"

val json4sVersion = "3.7.0-M10"
val circeVersion = "0.13.0"

resolvers ++=
  Resolver.bintrayRepo("commercetools", "maven") ::
  Resolver.bintrayRepo("rallyhealth", "maven")   ::
  Nil

libraryDependencies ++=
  "io.circe"                              %% "circe-generic"         % circeVersion  ::
  "io.circe"                              %% "circe-parser"          % circeVersion  ::
  "com.fasterxml.jackson.module"          %% "jackson-module-scala"  % "2.12.2"      ::
  "org.json4s"                            %% "json4s-native"         % json4sVersion ::
  "org.json4s"                            %% "json4s-jackson"        % json4sVersion ::
  "io.sphere"                             %% "sphere-json"           % "0.12.1"      ::
  "com.typesafe.play"                     %% "play-json"             % "2.9.2"       ::
  "io.spray"                              %% "spray-json"            % "1.3.6"       ::
  "io.argonaut"                           %% "argonaut"              % "6.3.3"       ::
  "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-macros" % "2.6.4"       ::
  "com.lihaoyi"                           %% "upickle"               % "1.2.3"       ::
  "com.phylage"                           %% "refuel-json"           % "1.4.10"       ::
  "io.bullet"                             %% "borer-derivation"      % "1.6.3"       ::
  "com.rallyhealth"                       %% "weepickle-v1"          % "1.4.0"       ::
  Nil

libraryDependencies ++=
  "org.scalatest"                         %% "scalatest"             % "3.2.3"       ::
  Nil map (_ % Test)

parallelExecution in Test := false

enablePlugins(JmhPlugin)
