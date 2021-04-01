ThisBuild / scalaVersion     := "2.13.4"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.2.2"
lazy val cats = "org.typelevel" %% "cats-effect" % "3.0.0"

lazy val root = (project in file("."))
  .settings(
    name := "Wrapped IO exercise",
    libraryDependencies += scalaTest % Test,
    libraryDependencies += cats
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.

