package ru.kode.base.internship.products.data.network.entity.deposit

import kotlinx.serialization.Serializable

@Serializable
data class Deposit(
  val balance: Int,
  val currency: String,
  val depositId: Int,
  val name: String,
  val status: String
)
