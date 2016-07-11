import play.PlayScala
import sbtrelease.ReleasePlugin

organization := "fr.rauricoste"

name := "request-mocker"

crossPaths := false

scalacOptions ++= Seq("-feature", "-deprecation", "-language:reflectiveCalls", "-language:postfixOps")

resolvers ++=Seq(
  "typesafe repo" at "http://repo.typesafe.com/typesafe/releases/"
)

scalaVersion := "2.11.8"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(
  ws,
  "ch.qos.logback"         %  "logback-classic"    % "1.0.13",
  "com.typesafe.play"      %% "play-json"          % "2.3.2",
  "commons-io"             %  "commons-io"         % "2.4"
)

parallelExecution in Test := false

play.PlayImport.PlayKeys.playDefaultPort := 9001

ReleasePlugin.releaseSettings
