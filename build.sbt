name := "tubaina"

version := "2.0"

scalaVersion := "2.9.1"

resolvers += "jboss" at "https://repository.jboss.org/nexus/content/groups/public/"

resolvers += "javanet"   at "http://download.java.net/maven/2/"

libraryDependencies <+= scalaVersion((v:String) => "org.scalatest" % ("scalatest_" + v) % "1.6.1" % "test")

libraryDependencies += "com.novocode" % "junit-interface" % "0.8" % "test"

testOptions += Tests.Argument(TestFrameworks.JUnit, "-q", "-v")

libraryDependencies ++= Seq(
	"de.java2html" % "java2html" % "5.0" % "compile",
	"log4j" % "log4j" % "1.2.12" % "compile",
	"commons-io" % "commons-io" % "1.3.1" % "compile",
	"commons-cli" % "commons-cli" % "1.1" % "compile",
	"net.vidageek" % "mirror" % "1.5.1" % "compile",
	"org.freemarker" % "freemarker" % "2.3.10" % "compile",
	"de.java2html" % "java2html" % "5.0" % "compile",
	"com.google.inject" % "guice" % "3.0" % "compile",
	"org.jmock" % "jmock" % "2.5.1" % "test",
	"junit" % "junit" % "4.9" % "test"
	)
