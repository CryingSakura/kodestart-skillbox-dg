package ru.kode.base.internship.products.domain.entity

import androidx.compose.runtime.Immutable
import ru.kode.base.internship.products.domain.CardStatus
import ru.kode.base.internship.products.domain.CardType
import ru.kode.base.internship.products.domain.PaymentSystem

@Immutable
data class CardDomainEntity(
  val cardId: CardDomainEntity.Id,
  val accountId: AccountDomainEntity.Id,
  val number: String,
  val expiredAt: String?,
  val status: CardStatus,
  val name: String,
  val cardType: CardType,
  val paymentSystem: PaymentSystem
) {
  @JvmInline
  value class Id(val cardId: String)
}
