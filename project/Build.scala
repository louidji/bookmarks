import sbt._
import Keys._
import play.Project._
//import cloudbees.Plugin._

object ApplicationBuild extends Build {

  val appName         = "bookmarks"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    anorm,
    "mysql" % "mysql-connector-java" % "5.1.23"
    //"com.typesafe.slick" % "slick_2.10" % "1.0.0-RC2"
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here

  ) //.settings(cloudBeesSettings :_*).settings(CloudBees.applicationId := Some(""))

}
