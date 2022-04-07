name := "peaceland"

version := "1.0"

scalaVersion := "2.13.3"

val akkaVersion = "2.6.8"
val akkaHttpVersion = "10.2.8"
val scalaTestVersion = "3.0.5"

/*
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.3" % "test"

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.9.2"
*/

libraryDependencies ++= Seq(
    //"org.scalatest" % "scalatest_2.12" % "3.2.3" % "test",
    //"com.github.stevenchen3" %% "scala-faker" % "0.1.1",
    "com.typesafe.play" %% "play-json" % "2.9.2",
    "org.apache.kafka" %% "kafka" % "2.7.0",
    "org.apache.spark" %% "spark-core" % "latest.integration",
    "org.apache.spark" %% "spark-sql" % "latest.integration",
    "org.plotly-scala" %% "plotly-render" % "latest.integration",
    "org.apache.spark" %% "spark-sql-kafka-0-10" % "latest.integration",
     // https://mvnrepository.com/artifact/com.oracle.database.jdbc/ojdbc8
   "com.oracle.database.jdbc" % "ojdbc8" % "21.3.0.0",

// https://mvnrepository.com/artifact/com.oracle.ojdbc/orai18n
     "com.oracle.ojdbc" % "orai18n" %  "19.3.0.0",

     "com.github.tototoshi" %% "scala-csv" % "1.3.8", 

     ///------------ TEST POUR POSTGRES ----------
     "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  // akka streams
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  // akka http
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion,
  // testing
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,

  // Slick and Postgres
  "com.typesafe.slick" %% "slick" % "3.3.3",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.3.3",
  "org.postgresql" % "postgresql" % "9.4-1206-jdbc42",

"org.apache.hadoop" % "hadoop-common" % "3.3.1",

"org.apache.hadoop" % "hadoop-client" % "3.3.1",

"org.apache.hadoop" % "hadoop-hdfs" % "3.3.1" % Test,

"org.apache.hadoop" % "hadoop-hdfs-client" % "3.3.1",
)
