package ru.kode.base.internship.products.data.network.entity.account

import kotlinx.serialization.Serializable

@Serializable
data class Card(
  val card_id: String,
  val card_type: String,
  val name: String,
  val number: String,
  val payment_system: String,
  val status: String,
)
