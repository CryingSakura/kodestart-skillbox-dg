package ru.kode.base.internship.products.data

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
import kotlin.random.Random

val accountId1: String = randomUuid()
val accountId2: String = randomUuid()
val depositId1: String = randomUuid()
val depositId2: String = randomUuid()
val depositId3: String = randomUuid()
val cardId1: String = randomUuid()
val cardId2: String = randomUuid()
val cardId3: String = randomUuid()

val card1 = CardDataEntity(
  accountId = accountId1,
  number = "1111222233334444",
  expiredAt = "2022-04-21T00:00:00Z",
  status = CardStatus.valueOf("Active"),
  name = "test1",
  cardType = CardType.valueOf("Physical"),
  paymentSystem = PaymentSystem.valueOf("Visa"),
  cardId = CardDataEntity.Id(cardId1)
)

val card2 = CardDataEntity(
  accountId = accountId1,
  number = "1111222233334444",
  expiredAt = "2022-04-21T00:00:00Z",
  status = CardStatus.valueOf("Active"),
  name = "test2",
  cardType = CardType.valueOf("Virtual"),
  paymentSystem = PaymentSystem.valueOf("Visa"),
  cardId = CardDataEntity.Id(cardId2)
)

val card3 = CardDataEntity(
  accountId = accountId1,
  number = "1111222233334444",
  expiredAt = "2022-04-21T00:00:00Z",
  status = CardStatus.valueOf("Active"),
  name = "test2",
  cardType = CardType.valueOf("Physical"),
  paymentSystem = PaymentSystem.valueOf("Visa"),
  cardId = CardDataEntity.Id(cardId3)
)

object MockGetData {
  fun getDeposits(): List<DepositDataEntity> {
    return listOf(
      DepositDataEntity(
        depositId = DepositDataEntity.Id(depositId1),
        balance = if (Random.nextBoolean())"5000" else "5555",
        status = DepositStatus.valueOf("Active"),
        rate = "13.0",
        closeDate = "01.01.2023",
        currency = if (Random.nextBoolean()) Currency.RUB else Currency.EUR,
        name = "Savings Deposit",
      ),
      DepositDataEntity(
        depositId = DepositDataEntity.Id(depositId2),
        balance = if (Random.nextBoolean())"10000" else "1111",
        status = DepositStatus.valueOf("Active"),
        rate = "11.5",
        closeDate = "06.30.2022",
        currency = if (Random.nextBoolean()) Currency.USD else Currency.RUB,
        name = "Savings Deposit",
      ),
      DepositDataEntity(
        depositId = DepositDataEntity.Id(depositId3),
        balance = if (Random.nextBoolean()) "2013400" else "2222",
        status = DepositStatus.valueOf("Active"),
        rate = "7.5",
        closeDate = "12.31.2021",
        currency = if (Random.nextBoolean()) Currency.EUR else Currency.USD,
        name = "Term Deposit",
      )
    )
  }

  fun getAccounts(): List<AccountDataEntity> {
    return listOf(
      AccountDataEntity(
        cards = listOf(
          card1,
          card2
        ),
        number = "1234567890",
        status = AccountStatus.valueOf("Active"),
        balance = if (Random.nextBoolean()) "1000" else "2",
        currency = Currency.valueOf("USD"),
        accountId = AccountDataEntity.Id(accountId1)
      ),
      AccountDataEntity(
        cards = listOf(
          card3
        ),
        number = "0987654321",
        status = AccountStatus.valueOf("Active"),
        balance = if (Random.nextBoolean()) "13240" else "1",
        currency = Currency.valueOf("USD"),
        accountId = AccountDataEntity.Id(accountId2)
      ),
    )
  }
  fun getCard(): List<CardDataEntity> {
    return listOf(
      card1,
      card2,
      card3
    )
  }
}
