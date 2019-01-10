val AkkaVersion = "2.5.17"
val AkkaHttpVersion = "10.0.14"
val AkkaManagementVersion = "0.20.0+4-8c494a9a+20181211-1127"
val PlaySocketIoVersion = "1.0.0-beta-2"

lazy val `play-clustered-chat` = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    organization := "com.example",
    version := "0.2",
    scalaVersion := "2.12.8",

    libraryDependencies ++= Seq(
      "com.lightbend.play" %% "play-socket-io" % PlaySocketIoVersion,
      "com.softwaremill.macwire" %% "macros" % "2.3.0" % Provided,

      "com.typesafe.akka" %% "akka-cluster" % AkkaVersion,
      "com.typesafe.akka" %% "akka-cluster-tools" % AkkaVersion,
      "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
      "com.lightbend.akka.discovery" %% "akka-discovery-kubernetes-api" % AkkaManagementVersion,
      "com.lightbend.akka.management" %% "akka-management-cluster-bootstrap" % AkkaManagementVersion,
      "com.lightbend.akka.management" %% "akka-management-cluster-http" % AkkaManagementVersion
    )
  )
