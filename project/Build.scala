import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "play-test-app"
  val appVersion      = "1.0-SNAPSHOT"
  val casbah = "org.mongodb" %% "casbah" % "2.5.0"
  val postgres = "postgresql" % "postgresql" % "8.4-702.jdbc4"

  val appDependencies = Seq(
    // Add your project dependencies here,
    postgres,
	casbah,
    jdbc,
    anorm
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}
