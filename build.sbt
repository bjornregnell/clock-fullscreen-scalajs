enablePlugins(ScalaJSPlugin)

name := "clock"
scalaVersion := "3.6.4"

scalaJSUseMainModuleInitializer := true 

libraryDependencies += ("org.scala-js" %%% "scalajs-dom" % "2.8.0") 

Compile / fastLinkJS / scalaJSLinkerOutputDirectory := target.value / "js-dev"

Compile / fullLinkJS / scalaJSLinkerOutputDirectory := target.value / "js"

Compile / fullLinkJS := {
  val report = (Compile / fullLinkJS).value
  val sourceFile = baseDirectory.value / "target" / "js" / "main.js"
  val targetFile = baseDirectory.value / "main.js"
  IO.copyFile(sourceFile, targetFile)
  println(s"Copy from:\n$sourceFile\nto:\n$targetFile")
  report
}