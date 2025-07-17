lazy val root = (project in file("."))
  .settings(
    name := "testing",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.18" % Test
  )