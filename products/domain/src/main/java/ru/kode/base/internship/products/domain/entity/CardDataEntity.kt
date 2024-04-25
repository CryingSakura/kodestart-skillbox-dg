package ru.kode.base.internship.products.domain.entity

import ru.kode.base.internship.products.domain.CardStatus
import ru.kode.base.internship.products.domain.CardType
import ru.kode.base.internship.products.domain.PaymentSystem

data class CardDataEntity(
  val cardId: CardDataEntity.Id,
  val accountId: String,
  val number: String,
  val expiredAt: String,
  val status: CardStatus,
  val name: String,
  val cardType: CardType,
  val paymentSystem: PaymentSystem
) {
  @JvmInline
  value class Id(val cardId: String)
}
