name := """json-perf"""

version := "1.0"

scalaVersion := "2.12.4"

val json4sVersion = "3.5.3"
val playVersion = "2.6.7"

resolvers += Resolver.bintrayRepo("commercetools", "maven")

libraryDependencies ++=
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.9.2"       ::
  "org.json4s"                   %% "json4s-native"        % json4sVersion ::
  "org.json4s"                   %% "json4s-jackson"       % json4sVersion ::
  "io.sphere"                    %% "sphere-json"          % "0.9.0"       ::
  "com.typesafe.play"            %% "play-json"            % playVersion   ::
  "io.spray"                     %% "spray-json"           % "1.3.4"       ::
  "io.argonaut"                  %% "argonaut"             % "6.2"         ::
  Nil

libraryDependencies ++=
  "org.scalatest"                %% "scalatest"            % "3.0.4"       ::
  Nil map (_ % Test)

parallelExecution in Test := false

enablePlugins(JmhPlugin)
