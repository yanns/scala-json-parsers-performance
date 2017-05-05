name := """json-perf"""

version := "1.0"

scalaVersion := "2.11.11"

val json4sVersion = "3.5.2"
val playVersion = "2.5.14"

resolvers += Resolver.bintrayRepo("commercetools", "maven")

libraryDependencies ++=
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.8.8"       ::
  "org.json4s"                   %% "json4s-native"        % json4sVersion ::
  "org.json4s"                   %% "json4s-jackson"       % json4sVersion ::
  "io.sphere"                    %% "sphere-json"          % "0.6.12"      ::
  "com.typesafe.play"            %% "play-json"            % playVersion   ::
  "io.spray"                     %% "spray-json"           % "1.3.3"       ::
  "io.argonaut"                  %% "argonaut"             % "6.2"         ::
  Nil

libraryDependencies ++=
  "org.scalatest"                %% "scalatest"            % "3.0.3"       ::
  Nil map (_ % Test)

testFrameworks += new TestFramework("org.scalameter.ScalaMeterFramework")

parallelExecution in Test := false

enablePlugins(JmhPlugin)
