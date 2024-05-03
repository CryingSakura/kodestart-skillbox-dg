package ru.kode.base.internship.products.data.network.entity.card

import kotlinx.serialization.Serializable

@Serializable
data class ResponseCardParamsById(
  val accountId: Int,
  val expiredAt: String,
  val id: Int,
  val name: String,
  val number: String,
  val paymentSystem: String,
  val status: String
)
