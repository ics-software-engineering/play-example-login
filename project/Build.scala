import com.github.play2war.plugin.{Play2WarKeys, Play2WarPlugin}
import sbt._


object ApplicationBuild extends Build {

  val appName         = "softservice"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
  )

  val main = play.Project(appName, appVersion, appDependencies)
    .settings(Play2WarPlugin.play2WarSettings: _*)
    .settings(
      // Add your own project settings here
      Play2WarKeys.servletVersion := "3.1"
    )

}