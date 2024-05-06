package ru.kode.base.internship.products.data.network.entity.account

import kotlinx.serialization.Serializable

@Serializable
data class Account(
  val accountId: Int,
  val balance: Int,
  val cards: List<Card>,
  val currency: String,
  val number: String,
  val status: String,
)
