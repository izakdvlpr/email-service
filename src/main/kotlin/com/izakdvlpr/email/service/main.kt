package com.izakdvlpr.email.service

fun main() {
  val emailService = EmailService(
    SMTPConfig(
      host = "smtp.ethereal.email",
      port = 587,
      username = "gregory.torp66@ethereal.email",
      password = "p5X4qk1VvzDNX8Qb8p"
    )
  )

  emailService.send(
    "gregory.torp66@ethereal.email",
    "izakdvlpr@gmail.com",
    "SMTP Test",
    "Hello Kotlin"
  )
}