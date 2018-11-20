name := "MyWarRoom"

version := "1.0"

scalaVersion := "2.11.7"

resolvers ++= Seq(
  "AWS release" at "http://maven-us.everstring.com:8081/nexus/content/repositories/releases",
  "AWS snapshot" at "http://maven-us.everstring.com:8081/nexus/content/repositories/snapshots",
  Resolver.sonatypeRepo("public")
)

libraryDependencies ++= Seq(
  "edu.stanford.nlp" % "stanford-corenlp" % "3.5.2",
  "org.scalatest" %% "scalatest" % "3.0.0-M9",
  "org.jsoup" %  "jsoup" % "1.6.1",
  "com.gravity" %% "goose" % "2.1.25-SNAPSHOT",
  "com.syncthemall" % "boilerpipe" % "1.2.2",
  "com.everstring.common" %% "utils" % "0.1.4-SNAPSHOT"
)

// disable publishing the main API jar
publishArtifact in(Compile, packageDoc) := false

// disable publishing the main sources jar
publishArtifact in(Compile, packageSrc) := false

// If you still wish to publish your assembled artifact along with the publish task and all of the other artifacts,
// add an assembly classifier (or other)
artifact in (Compile, assembly) := {
  val art = (artifact in (Compile, assembly)).value
  art.withClassifier(Some("assembly"))
}

addArtifact(artifact in (Compile, assembly), assembly)

assemblyMergeStrategy in assembly := {
  //case "application.conf" => MergeStrategy.concat
  //case "org/apache/spark/unused/UnusedStubClass.class" => MergeStrategy.discard
  //case "META-INF/MANIFEST.MF" => MergeStrategy.discard
  //case f if f.matches("META-INF.*\\.(SF|DSA|RSA)$") => MergeStrategy.discard
  //case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
  case x => MergeStrategy.first
}
    
