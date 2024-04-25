package ru.kode.base.internship.products.data

import ru.kode.base.core.di.AppScope
import ru.kode.base.core.di.SingleIn
import ru.kode.base.core.util.randomUuid
import ru.kode.base.internship.products.domain.AccountStatus
import ru.kode.base.internship.products.domain.CardStatus
import ru.kode.base.internship.products.domain.CardType
import ru.kode.base.internship.products.domain.Currency
import ru.kode.base.internship.products.domain.DepositStatus
import ru.kode.base.internship.products.domain.PaymentSystem
import ru.kode.base.internship.products.domain.entity.AccountDataEntity
import ru.kode.base.internship.products.domain.entity.CardDataEntity
import ru.kode.base.internship.products.domain.entity.DepositDataEntity

val accountId1: String = randomUuid()
val accountId2: String = randomUuid()
val depositId1: String = randomUuid()
val depositId2: String = randomUuid()
val depositId3: String = randomUuid()
val cardId1: String = randomUuid()
val cardId2: String = randomUuid()
val cardId3: String = randomUuid()

object MockGetData {

  fun getDeposits(): List<DepositDataEntity> {
    return listOf(
      DepositDataEntity(
        depositId = DepositDataEntity.Id(depositId1),
        balance = "5000",
        status = DepositStatus.valueOf("Active"),
        rate = "13.0",
        closeDate = "01.01.2023",
        currency = Currency.valueOf("RUB"),
        name = "Savings Deposit",
      ),
      DepositDataEntity(
        depositId = DepositDataEntity.Id(depositId2),
        balance = "10000",
        status = DepositStatus.valueOf("Active"),
        rate = "11.5",
        closeDate = "06.30.2022",
        currency = Currency.valueOf("USD"),
        name = "Savings Deposit",
      ),
      DepositDataEntity(
        depositId = DepositDataEntity.Id(depositId3),
        balance = "2000",
        status = DepositStatus.valueOf("Active"),
        rate = "7.5",
        closeDate = "12.31.2021",
        currency = Currency.valueOf("USD"),
        name = "Term Deposit",
      )
    )
  }

  fun getAccounts(): List<AccountDataEntity> {
    return listOf(
      AccountDataEntity(
        cardsId = listOf(
          CardDataEntity(
            accountId = accountId1,
            number = "1111222233334444",
            expiredAt = "2022-04-21T00:00:00Z",
            status = CardStatus.valueOf("Active"),
            name = "test1",
            cardType = CardType.valueOf("physical"),
            paymentSystem = PaymentSystem.valueOf("Visa"),
            cardId = CardDataEntity.Id(cardId1)
          ),
          CardDataEntity(
            accountId = accountId1,
            number = "5555666677778888",
            expiredAt = "2022-04-21T00:00:00Z",
            status = CardStatus.valueOf("Blocked"),
            name = "test2",
            cardType = CardType.valueOf("virtual"),
            paymentSystem = PaymentSystem.valueOf("Mastercard"),
            cardId = CardDataEntity.Id(cardId2)
          ),
        ),
        number = "1234567890",
        status = AccountStatus.valueOf("Active"),
        balance = "10000",
        currency = Currency.valueOf("USD"),
        accountId = AccountDataEntity.Id(accountId1)
      ),
      AccountDataEntity(
        cardsId = listOf(
          CardDataEntity(
            accountId = accountId2,
            number = "5555666677778888",
            expiredAt = "2022-04-21T00:00:00Z",
            status = CardStatus.valueOf("Active"),
            name = "test3",
            cardType = CardType.valueOf("virtual"),
            paymentSystem = PaymentSystem.valueOf("Mastercard"),
            cardId = CardDataEntity.Id(cardId3)
          )
        ),
        number = "0987654321",
        status = AccountStatus.valueOf("Active"),
        balance = "10000",
        currency = Currency.valueOf("USD"),
        accountId = AccountDataEntity.Id(accountId2)
      ),
    )
  }
  fun getCard(): List<CardDataEntity> {
    return listOf(
      CardDataEntity(
        accountId = accountId1,
        number = "1111222233334444",
        expiredAt = "2022-04-21T00:00:00Z",
        status = CardStatus.valueOf("Active"),
        name = "test1",
        cardType = CardType.valueOf("physical"),
        paymentSystem = PaymentSystem.valueOf("Visa"),
        cardId = CardDataEntity.Id(cardId1)
      ),
      CardDataEntity(
        accountId = accountId1,
        number = "5555666677778888",
        expiredAt = "2022-04-21T00:00:00Z",
        status = CardStatus.valueOf("Blocked"),
        name = "test2",
        cardType = CardType.valueOf("virtual"),
        paymentSystem = PaymentSystem.valueOf("Mastercard"),
        cardId = CardDataEntity.Id(cardId2)
      ),
      CardDataEntity(
        accountId = accountId2,
        number = "5555666677778888",
        expiredAt = "2022-04-21T00:00:00Z",
        status = CardStatus.valueOf("Active"),
        name = "test3",
        cardType = CardType.valueOf("virtual"),
        paymentSystem = PaymentSystem.valueOf("Mastercard"),
        cardId = CardDataEntity.Id(cardId3)
      )
    )
  }
}
