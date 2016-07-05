import com.github.play2war.plugin.{Play2WarKeys, Play2WarPlugin}

name := "play-example-login"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache
)

play.Project.playJavaSettings

Play2WarPlugin.play2WarSettings

Play2WarKeys.servletVersion := "3.1"