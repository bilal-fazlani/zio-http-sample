ThisBuild / scalaVersion := "3.2.1"
ThisBuild / version := "0.1.0-SNAPSHOT"

lazy val sample = (project in file("."))
  .settings(
    name := "sample",
    libraryDependencies ++= Seq(
      Libs.zioHttp
    )
  )
