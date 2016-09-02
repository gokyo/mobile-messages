/*
 * Copyright 2016 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.mobilemessages.acceptance

import com.github.tomakehurst.wiremock.client.WireMock
import org.scalatest.concurrent.{IntegrationPatience, ScalaFutures}
import org.scalatest.mock.MockitoSugar
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach}
import play.api.test.{FakeApplication, FakeRequest}
import play.api.{GlobalSettings, Play}
import uk.gov.hmrc.crypto.CryptoWithKeysFromConfig
import uk.gov.hmrc.domain.SaUtr
import uk.gov.hmrc.mobilemessages.acceptance.microservices.{AuthService, MessageService, SaMessageRendererService}
import uk.gov.hmrc.mobilemessages.acceptance.utils.WiremockServiceLocatorSugar
import uk.gov.hmrc.mobilemessages.controllers.{LiveMobileMessagesController, MobileMessagesController}
import uk.gov.hmrc.mobilemessages.utils.ConfigHelper.microserviceConfigPathFor
import uk.gov.hmrc.play.test.UnitSpec

trait AcceptanceSpec extends UnitSpec
  with MockitoSugar
  with ScalaFutures
  with WiremockServiceLocatorSugar
  with BeforeAndAfterAll
  with BeforeAndAfterEach
  with IntegrationPatience {

  override def beforeAll() = {
    super.beforeAll()
    Play.start(app)
    startMockServer()
    saMessageRenderer.start()
  }

  override def afterAll() = {
    super.afterAll()
    Play.stop()
    stopMockServer()
    saMessageRenderer.stop()
  }

  override protected def afterEach() = {
    super.afterEach()
    saMessageRenderer.reset()
    WireMock.reset()
  }

  lazy val messageController: MobileMessagesController = LiveMobileMessagesController

  val utr = SaUtr("109238")

  val auth = new AuthService
  val message = new MessageService(auth.token)
  val saMessageRenderer = new SaMessageRendererService(auth.token)
  lazy val configBasedCryptor = CryptoWithKeysFromConfig(baseConfigKey = "queryParameter.encryption")

  val mobileMessagesGetRequest = FakeRequest("GET", "/").
    withHeaders(
      ("Accept", "application/vnd.hmrc.1.0+json"),
      ("Authorization", auth.token)
    )

  object TestGlobal extends GlobalSettings

  implicit val app = FakeApplication(
    withGlobal = Some(TestGlobal),
    additionalConfiguration = Map(
      "appName" -> "application-name",
      "appUrl" -> "http://microservice-name.service",
      s"${microserviceConfigPathFor("auth")}.host" -> stubHost,
      s"${microserviceConfigPathFor("auth")}.port" -> stubPort,
      s"${microserviceConfigPathFor("message")}.host" -> stubHost,
      s"${microserviceConfigPathFor("message")}.port" -> stubPort,
      "auditing.enabled" -> "false",
      "queryParameter.encryption.key" -> "kepODU8hulPkolIryPOrTY=="
    ) ++ saMessageRenderer.config
  )
}
