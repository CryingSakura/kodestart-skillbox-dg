package ru.kode.base.internship.products.data.network.entity.account

import kotlinx.serialization.Serializable

@Serializable
data class AccountsResponse(
  val accounts: List<Account>,
)
