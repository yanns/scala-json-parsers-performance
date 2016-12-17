name := """json-perf"""

version := "1.0"

scalaVersion := "2.11.8"

val json4sVersion = "3.5.0"
val playVersion = "2.5.10"

resolvers += Resolver.bintrayRepo("commercetools", "maven")

libraryDependencies ++=
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.8.4"       ::
  "org.json4s"                   %% "json4s-native"        % json4sVersion ::
  "org.json4s"                   %% "json4s-jackson"       % json4sVersion ::
  "io.sphere"                    %% "sphere-json"          % "0.6.8"       ::
  "com.typesafe.play"            %% "play-json"            % playVersion   ::
  "io.spray"                     %% "spray-json"           % "1.3.2"       ::
  "io.argonaut"                  %% "argonaut"             % "6.1"         ::
  Nil

libraryDependencies ++=
  "org.scalatest"                %% "scalatest"            % "3.0.1"       ::
  Nil map (_ % Test)

testFrameworks += new TestFramework("org.scalameter.ScalaMeterFramework")

parallelExecution in Test := false

enablePlugins(JmhPlugin)
