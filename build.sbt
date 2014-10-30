name := "play-example-login"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache
)

libraryDependencies += "org.mindrot"  % "jbcrypt"   % "0.3m"

play.Project.playJavaSettings
