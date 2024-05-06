package ru.kode.base.internship.products.domain.entity

import androidx.compose.runtime.Immutable
import ru.kode.base.internship.products.domain.AccountStatus
import ru.kode.base.internship.products.domain.Currency

@Immutable
data class AccountDomainEntity(
  val cards: List<CardDomainEntity>,
  val number: String,
  val status: AccountStatus,
  val balance: String,
  val currency: Currency,
  val accountId: AccountDomainEntity.Id,
) {
  @JvmInline
  value class Id(val accountId: String)
}
