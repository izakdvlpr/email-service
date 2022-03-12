package com.izakdvlpr.email.service

data class EmailData(
  val from: String,
  val to: String,
  val subject: String,
  val text: String
)
