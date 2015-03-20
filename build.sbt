import java.lang.Boolean.{ parseBoolean => bool }

organization := "me.lessis"

version := "0.1.4"

name := "semverfi"

description := "Always Faithful, always loyal semantic versions"

homepage := Some(url("https://github.com/softprops/semverfi"))

crossScalaVersions := Seq("2.11.6", "2.10.5")

scalaVersion := "2.11.6"

resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"

scalacOptions += "-deprecation"

libraryDependencies := {
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, scalaMajor)) if scalaMajor >= 11 =>
      libraryDependencies.value ++ Seq(
        "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.3",
        "org.specs2" % "specs2_2.11" % "2.4.17"
      )
    case _ =>
      // or just libraryDependencies.value if you don't depend on scala-swing
      libraryDependencies.value ++ Seq( 
        "org.scala-lang" % "scala-swing" % scalaVersion.value,
        "org.specs2" %% "specs2" % "2.4.17"
      )
  }
}

publishMavenStyle := true

publishTo := Some(Opts.resolver.sonatypeStaging)

publishArtifact in Test := false

licenses <<= version(v => Seq("MIT" -> url("https://github.com/softprops/semverfi/blob/%s/LICENSE" format v)))

pomExtra := (
  <scm>
    <url>git@github.com:softprops/semverfi.git</url>
    <connection>scm:git:git@github.com:softprops/semverfi.git</connection>
  </scm>
  <developers>
    <developer>
      <id>softprops</id>
      <name>Doug Tangren</name>
      <url>https://github.com/softprops</url>
    </developer>
  </developers>)

logLevel in Global := { if (bool(sys.env.getOrElse("TRAVIS", "false"))) Level.Warn else Level.Info }

logLevel in Compile := { if (bool(sys.env.getOrElse("TRAVIS", "false"))) Level.Warn else Level.Info }

logLevel in Test := { if (bool(sys.env.getOrElse("TRAVIS", "false"))) Level.Info else Level.Info }

seq(lsSettings:_*)

LsKeys.tags in LsKeys.lsync := Seq("semver", "version")
