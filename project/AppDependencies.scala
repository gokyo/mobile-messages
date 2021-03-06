import sbt._

object AppDependencies {
  import play.core.PlayVersion
  import play.sbt.PlayImport._

  private val bootstrapPlayVersion = "1.5.0"
  private val authClientVersion = "2.6.0"
  private val domainVersion = "5.1.0"
  private val playHmrcApiVersion = "2.1.0"

  private val reactiveCircuitBreaker = "3.2.0"
  private val emailAddress = "2.1.0"

  private val crypto = "4.5.0"
  private val reactiveMongoBson = "0.14.0"

  private val wireMockVersion = "2.3.1"
  private val hmrcTestVersion = "3.0.0"
  private val cucumberVersion = "1.2.5"
  private val mockitoVersion = "2.0.2-beta"
  private val scalatestplusPlayVersion = "2.0.1"

  val compile = Seq(
    ws,
    "uk.gov.hmrc" %% "bootstrap-play-25" % bootstrapPlayVersion,
    "uk.gov.hmrc" %% "auth-client" % authClientVersion,
    "uk.gov.hmrc" %% "play-hmrc-api" % playHmrcApiVersion,
    "uk.gov.hmrc" %% "domain" % domainVersion,
    "uk.gov.hmrc" %% "reactive-circuit-breaker" % reactiveCircuitBreaker,
    "uk.gov.hmrc" %% "emailaddress" % emailAddress,
    "uk.gov.hmrc" %% "crypto" % crypto,
    "uk.gov.hmrc" %% "reactivemongo-bson" % reactiveMongoBson //NOTE: this is included purely for sandbox object creation
  )

  trait TestDependencies {
    lazy val scope: String = "test"
    lazy val test : Seq[ModuleID] = ???
  }

  object Test {
    def apply() = new TestDependencies {
      override lazy val test = Seq(
        "uk.gov.hmrc" %% "hmrctest" % hmrcTestVersion % scope,
        "org.scalamock"     %% "scalamock-scalatest-support" % "3.2.2" % scope,
        "com.github.tomakehurst" % "wiremock" % wireMockVersion % scope,
        "org.mockito" % "mockito-core" % mockitoVersion % scope,
        "org.scalatestplus.play" %% "scalatestplus-play" % scalatestplusPlayVersion % scope
      )
    }.test
  }

  object IntegrationTest {
    def apply() = new TestDependencies {

      override lazy val scope: String = "it"

      override lazy val test = Seq(
        "uk.gov.hmrc" %% "hmrctest" % hmrcTestVersion % scope,
        "com.typesafe.play" %% "play-test" % PlayVersion.current % scope,
        "info.cukes" %% "cucumber-scala" % cucumberVersion % scope,
        "info.cukes" % "cucumber-junit" % cucumberVersion % scope,
        "com.github.tomakehurst" % "wiremock" % wireMockVersion % scope,
        "org.mockito" % "mockito-all" % mockitoVersion % scope,
        "org.scalatestplus.play" %% "scalatestplus-play" % scalatestplusPlayVersion % scope
      )
    }.test
  }

  def apply() = compile ++ Test() ++ IntegrationTest()
}

