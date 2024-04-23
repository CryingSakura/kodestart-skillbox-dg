package ru.kode.base.internship.ui.home

import androidx.compose.runtime.Immutable
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import ru.kode.base.core.util.randomUuid
import ru.kode.base.internship.core.domain.entity.LceState
import ru.kode.base.internship.ui.cardicon.PaymentSystem
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

val accountId1: String = randomUuid()
val accountId2: String = randomUuid()
val depositId1: String = randomUuid()
val depositId2: String = randomUuid()
val depositId3: String = randomUuid()
val cardId1: String = randomUuid()
val cardId2: String = randomUuid()
val cardId3: String = randomUuid()

enum class CardType {
  physical,
  virtual
}
enum class CardStatus {
  Active,
  Blocked,
}
enum class AccountStatus {
  Active,
  Blocked,
}
enum class DepositStatus {
  Active,
  Blocked,
}

enum class Currency {
  RUB,
  USD,
  EUR
}

data class Money(
  val balance: String,
  val currency: Currency
) {
  fun format(): String {
    return "$balance " + when (currency) {
      Currency.RUB -> "₽"
      Currency.USD -> "$"
      Currency.EUR -> "€"
    }
  }
}

@Immutable
data class Account(
  val cards: List<Card>,
  val number: String,
  val status: AccountStatus,
  val balance: String,
  val currency: Currency,
  val accountId: Id,
) {
  @JvmInline
  value class Id(val accountId: String)
}
@Immutable
data class Deposit(
  val depositId: Id,
  val balance: String,
  val status: DepositStatus,
  val rate: String,
  val closeDate: String,
  val currency: Currency,
  val name: String,
) {
  @JvmInline
  value class Id(val depositId: String)
}
@Immutable
data class Card(
  val cardId: Id,
  val accountId: String,
  val number: String,
  val expiredAt: String,
  val status: CardStatus,
  val name: String,
  val cardType: CardType,
  val paymentSystem: PaymentSystem,
) {
  @JvmInline
  value class Id(val cardId: String)
}

val deposit1 = Deposit(
  balance = "5000.00",
  status = DepositStatus.valueOf("Active"),
  rate = "11.5",
  closeDate = "01.01.2023",
  currency = Currency.valueOf("RUB"),
  name = "Savings Deposit",
  depositId = Deposit.Id(depositId1)
)

val deposit2 = Deposit(
  balance = "10000.00",
  status = DepositStatus.valueOf("Active"),
  rate = "13.0",
  closeDate = "06.30.2022",
  currency = Currency.valueOf("USD"),
  name = "Fixed Deposit",
  depositId = Deposit.Id(depositId2)
)

val deposit3 = Deposit(
  balance = "2000.00",
  status = DepositStatus.valueOf("Active"),
  rate = "7.5",
  closeDate = "12.31.2021",
  currency = Currency.valueOf("EUR"),
  name = "Term Deposit",
  depositId = Deposit.Id(depositId3)
)

val depositList = listOf(deposit1, deposit2, deposit3)

val card1 = Card(
  accountId = accountId1,
  number = "1111222233334444",
  expiredAt = "2022-04-21T00:00:00Z",
  status = CardStatus.valueOf("Active"),
  name = "test1",
  cardType = CardType.valueOf("physical"),
  paymentSystem = PaymentSystem.valueOf("Visa"),
  cardId = Card.Id(cardId1)
)

val card2 = Card(
  accountId = accountId1,
  number = "5555666677778888",
  expiredAt = "2022-04-21T00:00:00Z",
  status = CardStatus.valueOf("Blocked"),
  name = "test2",
  cardType = CardType.valueOf("virtual"),
  paymentSystem = PaymentSystem.valueOf("Mastercard"),
  cardId = Card.Id(cardId2)
)
val card3 = Card(
  accountId = accountId1,
  number = "5555666677778888",
  expiredAt = "2022-04-21T00:00:00Z",
  status = CardStatus.valueOf("Active"),
  name = "test3",
  cardType = CardType.valueOf("virtual"),
  paymentSystem = PaymentSystem.valueOf("Mastercard"),
  cardId = Card.Id(cardId3)
)

val account1 = Account(
  accountId = Account.Id(accountId1),
  cards = listOf(card1, card2),
  number = "1234567890",
  status = AccountStatus.valueOf("Active"),
  balance = "1000.00",
  currency = Currency.valueOf("EUR")
)
val account2 = Account(
  accountId = Account.Id(accountId2),
  cards = listOf(card3),
  number = "1234567890",
  status = AccountStatus.valueOf("Active"),
  balance = "1000.00",
  currency = Currency.valueOf("USD")
)

val accountList = listOf(account1, account2)

val accountsData = MutableStateFlow<List<Account>>(emptyList())
val depositsData = MutableStateFlow<List<Deposit>>(emptyList())

val dataAccountsState = MutableStateFlow<LceState>(LceState.None)
val dataDepositsState = MutableStateFlow<LceState>(LceState.None)

suspend fun fetchAccountsData() {
  dataAccountsState.value = LceState.Loading
  delay(2.seconds)
  if (Random.nextBoolean()) {
    dataAccountsState.value = LceState.Content
    accountsData.value = accountList
  } else {
    dataAccountsState.value = LceState.Error("Failed to load data")
  }
} suspend fun fetchDepositsData() {
  dataDepositsState.value = LceState.Loading
  delay(2.seconds)
  if (Random.nextBoolean()) {
    dataDepositsState.value = LceState.Content
    depositsData.value = depositList
  } else {
    dataDepositsState.value = LceState.Error("Failed to load data")
  }
}
