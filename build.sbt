ThisBuild / version := "0.0.1"

ThisBuild / scalaVersion := "2.13.8"

ThisBuild / organization := "fortyseven"

lazy val commonSettings = Seq(
  javacOptions ++= Seq(
    "-source",
    "11",
    "-target",
    "11"
  ),
  javaOptions ++= Seq(
    "-Xms512M",
    "-Xmx2048M",
    "-XX:MaxPermSize=2048M",
    "-XX:+CMSClassUnloadingEnabled"
  ),
  scalacOptions ++= Seq(
    "-encoding",
    "UTF-8",
    "-deprecation",
    "-unchecked",
    "-Xfatal-warnings"
  ),
  Test / parallelExecution := false,
  fork                     := true,
  coverageHighlighting     := true
)

lazy val root = (project in file("."))
  .settings(name := "howtotestspark")
  .settings(commonSettings)
  .settings(
    libraryDependencies ++= Seq(
      ("org.apache.spark" %% "spark-streaming" % "3.2.0"  % "provided")
        .exclude("javax.servlet", "javax.servlet-api")
        .exclude("org.glassfish", "javax.servlet")
        .exclude("org.eclipse.jetty.orbit", "javax.servlet"),
      ("org.apache.spark" %% "spark-sql"       % "3.2.0"  % "provided")
        .exclude("javax.servlet", "javax.servlet-api")
        .exclude("org.glassfish", "javax.servlet")
        .exclude("org.eclipse.jetty.orbit", "javax.servlet"),
      ("org.apache.spark" %% "spark-mllib"     % "3.2.0"  % "test")
        .exclude("javax.servlet", "javax.servlet-api")
        .exclude("org.glassfish", "javax.servlet")
        .exclude("org.eclipse.jetty.orbit", "javax.servlet"),
      ("org.apache.hive"   % "hive-common"     % "2.3.9"  % "test")
        .exclude("javax.servlet", "javax.servlet-api")
        .exclude("org.glassfish", "javax.servlet")
        .exclude("org.eclipse.jetty.orbit", "javax.servlet"),
      "junit"              % "junit"           % "4.12"   % "test",
      "org.scalatest"     %% "scalatest"       % "3.0.9"  % "test",
      "org.scalacheck"    %% "scalacheck"      % "1.15.2" % "test"
    )
  )
