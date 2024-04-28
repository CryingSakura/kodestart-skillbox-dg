package ru.kode.base.internship.products.domain.entity

import ru.kode.base.internship.products.domain.AccountStatus
import ru.kode.base.internship.products.domain.Currency

data class AccountDataEntity(
  val cards: List<CardDataEntity>,
  val number: String,
  val status: AccountStatus,
  val balance: String,
  val currency: Currency,
  val accountId: AccountDataEntity.Id,
) {
  @JvmInline
  value class Id(val accountId: String)
}
