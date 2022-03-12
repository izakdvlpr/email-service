package com.izakdvlpr.email.service

data class SMTPConfig(
  val host: String,
  val port: Int,
  val username: String?=null,
  val password: String?=null
)