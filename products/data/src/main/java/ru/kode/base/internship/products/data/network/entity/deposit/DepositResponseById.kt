package ru.kode.base.internship.products.data.network.entity.deposit

import kotlinx.serialization.Serializable

@Serializable
data class DepositResponseById(
  val balance: Int,
  val closeDate: String,
  val currency: String,
  val name: String,
  val rate: Double,
  val status: String
)
