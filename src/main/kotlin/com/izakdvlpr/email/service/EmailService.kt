package com.izakdvlpr.email.service

import com.izakdvlpr.email.service.utils.putIfMissing

import javax.mail.*
import javax.mail.internet.*

import java.util.*

class EmailService(private val config: SMTPConfig) {
  private fun getProperties(): Properties {
    val properties = Properties()

    putIfMissing(properties, "mail.smtp.host", config.host)
    putIfMissing(properties, "mail.smtp.port", config.port.toString())
    putIfMissing(properties, "mail.smtp.ssl.enable", "false")
    putIfMissing(properties, "mail.smtp.starttls.enable", "true")
    putIfMissing(properties, "mail.smtp.auth", "true")
    putIfMissing(properties, "mail.smtp.socketFactory.fallback", "false")

    return properties
  }

  private fun createSession(): Session {
    val session = Session.getDefaultInstance(getProperties(), object: Authenticator() {
      override fun getPasswordAuthentication(): PasswordAuthentication {
        return PasswordAuthentication(config.username, config.password)
      }
    })

    session.debug = true

    return session
  }

  private fun prepareEmailData(session: Session, data: EmailData): MimeMessage {
    val mimeMessage = MimeMessage(session)

    mimeMessage.setFrom(InternetAddress(data.from))
    mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(data.to, false))

    mimeMessage.subject = data.subject
    mimeMessage.setText(data.text)

    return mimeMessage
  }

  private fun makeTransport(session: Session, messageData: MimeMessage) {
    val transport = session.getTransport("smtp")

    transport.connect()
    transport.sendMessage(messageData, messageData.allRecipients)
    transport.close()
  }

  fun send(from: String, to: String, subject: String, text: String) {
    val session = createSession()

    try {
      val messageData = prepareEmailData(session, EmailData(from, to, subject, text))

      makeTransport(session, messageData)
    } catch (e: MessagingException) {
      e.printStackTrace()
    }
  }
}