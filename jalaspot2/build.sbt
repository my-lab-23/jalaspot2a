name := """jalaspot2"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.6"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
// https://mvnrepository.com/artifact/se.michaelthelin.spotify/spotify-web-api-java
libraryDependencies += "se.michaelthelin.spotify" % "spotify-web-api-java" % "7.0.0"

dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-databind" % "2.11.4"
dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-core" % "2.11.4"

libraryDependencies += "com.dropbox.core" % "dropbox-core-sdk" % "5.0.0"

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"
