import com.izakdvlpr.email.service.EmailService
import com.izakdvlpr.email.service.SMTPConfig

import org.junit.Before
import org.junit.Test

class EmailTest {
  private var emailService: EmailService? = null

  @Before
  fun setUp() {
    emailService = EmailService(
      SMTPConfig(
        host = "smtp.ethereal.email",
        port = 587,
        username = "gregory.torp66@ethereal.email",
        password = "p5X4qk1VvzDNX8Qb8p"
      )
    )
  }

  @Test
  fun testSend() {
    emailService!!.send(
      "gregory.torp66@ethereal.email",
      "izakdvlpr@gmail.com",
      "SMTP Test",
      "Hello Kotlin"
    )
  }
}