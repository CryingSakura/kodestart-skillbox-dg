package ru.kode.base.internship.products.data.mappers

import ru.kode.base.internship.products.data.CardEntity
import ru.kode.base.internship.products.data.network.entity.account.Card
import ru.kode.base.internship.products.data.network.entity.card.ResponseCardParamsById
import ru.kode.base.internship.products.domain.CardStatus
import ru.kode.base.internship.products.domain.CardType
import ru.kode.base.internship.products.domain.PaymentSystem
import ru.kode.base.internship.products.domain.entity.AccountDomainEntity
import ru.kode.base.internship.products.domain.entity.CardDomainEntity

internal fun CardEntity.toCardDm(): CardDomainEntity {
  return CardDomainEntity(
    cardId = CardDomainEntity.Id(cardId = id.toString()),
    accountId = AccountDomainEntity.Id(accountId = accountId.toString()),
    number = number,
    expiredAt = expiredAt!!,
    status = CardStatus.valueOf(status),
    name = name,
    cardType = CardType.valueOf(cardType!!),
    paymentSystem = PaymentSystem.valueOf(paymentSystem)
  )
}

internal fun CardDomainEntity.toCardEntity(): CardEntity {
  return CardEntity(
    id = cardId.cardId.toLong(),
    accountId = accountId.accountId.toLong(),
    name = name,
    number = number,
    paymentSystem = paymentSystem.name,
    status = status.name,
    cardType = cardType.name,
    expiredAt = expiredAt
  )
}

internal fun mapToDomainCard(
  card: Card,
  cardById: ResponseCardParamsById,
): CardDomainEntity {
  return CardDomainEntity(
    cardId = CardDomainEntity.Id(cardId = card.card_id),
    accountId = AccountDomainEntity.Id(accountId = cardById.accountId.toString()),
    number = card.number,
    expiredAt = cardById.expiredAt,
    status = CardStatus.valueOf(card.status),
    name = card.name,
    cardType = CardType.valueOf(card.card_type),
    paymentSystem = PaymentSystem.valueOf(card.payment_system)
  )
}
