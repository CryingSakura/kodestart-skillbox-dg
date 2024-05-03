package ru.kode.base.internship.products.data.network.entity.deposit

import kotlinx.serialization.Serializable

@Serializable
data class DepositsResponse(
  val deposits: List<Deposit>,
)
