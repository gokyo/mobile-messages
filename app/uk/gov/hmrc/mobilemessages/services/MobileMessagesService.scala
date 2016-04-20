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

package uk.gov.hmrc.mobilemessages.services

import uk.gov.hmrc.api.sandbox.FileResource
import uk.gov.hmrc.api.service.Auditor
import uk.gov.hmrc.mobilemessages.config.MicroserviceAuditConnector
import uk.gov.hmrc.mobilemessages.connector._
import uk.gov.hmrc.mobilemessages.domain.MessageHeader
import uk.gov.hmrc.play.audit.http.connector.AuditConnector
import uk.gov.hmrc.play.http.HeaderCarrier

import scala.concurrent.{ExecutionContext, Future}


trait MobileMessagesService {
  def ping()(implicit hc:HeaderCarrier, ec : ExecutionContext): Future[Boolean]

//  def readAndUnreadMessages()(implicit hc:HeaderCarrier, ec : ExecutionContext): Future[Seq[MessageHeader]]
}

trait LiveMobileMessagesService extends MobileMessagesService with Auditor {
  def authConnector: AuthConnector

  def ping()(implicit hc:HeaderCarrier, ec : ExecutionContext): Future[Boolean]

}

object SandboxMobileMessagesService extends MobileMessagesService with FileResource {

  def ping()(implicit hc:HeaderCarrier, ec : ExecutionContext): Future[Boolean] = Future.successful(true)

//  def readAndUnreadMessages()(implicit hc: HeaderCarrier, ec: ExecutionContext): Future[Seq[MessageHeader]] = {
//
//  }
}

object LiveMobileMessagesService extends LiveMobileMessagesService {
  override val authConnector: AuthConnector = AuthConnector

  val auditConnector: AuditConnector = MicroserviceAuditConnector

  def ping()(implicit hc:HeaderCarrier, ec : ExecutionContext): Future[Boolean] = Future.successful(true)
}
