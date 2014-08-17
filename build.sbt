name := "scalaj-spider"

version := "0.0.1-SNAPSHOT"

organization := "spider"

scalaVersion := "2.10.3"

libraryDependencies ++= Seq(
  "com.github.kristofa"%"mock-http-server"%"1.3",
  "junit"%"junit"%"4.10",
  "com.novocode"% "junit-interface"% "0.10",
  "org.scalaj"%"scalaj-http_2.10"%"0.3.16"        
)