name := """json-perf"""

version := "1.0"

scalaVersion := "2.11.7"

val json4sVersion = "3.2.11"
val playVersion = "2.4.2"

resolvers += Resolver.bintrayRepo("commercetools", "maven")

libraryDependencies ++=
  "com.storm-enroute"            %% "scalameter"           % "0.6"         ::
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.5.2"       ::
  "org.json4s"                   %% "json4s-native"        % json4sVersion ::
  "org.json4s"                   %% "json4s-jackson"       % json4sVersion ::
  "io.sphere"                    %% "sphere-json"          % "0.5.14"      ::
  "com.typesafe.play"            %% "play-json"            % playVersion   ::
  "io.spray"                     %% "spray-json"           % "1.3.2"       ::
  "io.argonaut"                  %% "argonaut"             % "6.1"         ::
  Nil

libraryDependencies ++=
  "com.storm-enroute"            %% "scalameter"           % "0.6"         ::
  Nil map (_ % Test)

testFrameworks += new TestFramework("org.scalameter.ScalaMeterFramework")

parallelExecution in Test := false

enablePlugins(JmhPlugin)
