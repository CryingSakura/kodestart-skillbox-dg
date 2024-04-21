package ru.kode.base.internship.ui.home

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import ru.kode.base.core.util.randomUuid
import ru.kode.base.internship.core.domain.entity.LceState
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds


val accountId1: String = randomUuid()
val accountId2: String = randomUuid()

data class Profile(
  val id: String,
  val firstName: String,
  val middleName: String?,
  val lastName: String,
  val country: String,
  val phone: String,
  val deposits: List<Deposit>,
  val accounts: List<Account>,
)

data class Account(
  val cards: List<Card>,
  val number: String,
  val status: String,
  val balance: String,
  val currency: String,
  val accountId: String,
)

data class Deposit(
  val depositId: String = randomUuid(),
  val balance: String,
  val status: String,
  val rate: String,
  val closeDate: String,
  val currency: String,
  val name: String,
)

data class Card(
  val id: String = randomUuid(),
  val accountId: String,
  val number: String,
  val expiredAt: String,
  val status: String,
  val name: String,
  val cardType: String,
  val paymentSystem: String,
)


val deposit1 = Deposit(
  balance = "5000.00",
  status = "Active",
  rate = "11.5",
  closeDate = "01.01.2023",
  currency = "RUB",
  name = "Savings Deposit"
)

val deposit2 = Deposit(
  balance = "10000.00",
  status = "Active",
  rate = "13.0",
  closeDate = "06.30.2022",
  currency = "USD",
  name = "Fixed Deposit"
)

val deposit3 = Deposit(
  balance = "2000.00",
  status = "Active",
  rate = "7.5",
  closeDate = "12.31.2021",
  currency = "EUR",
  name = "Term Deposit"
)

val depositList = listOf(deposit1, deposit2, deposit3)

val card1 = Card(
  accountId = accountId1,
  number = "1111222233334444",
  expiredAt = "2022-04-21T00:00:00Z",
  status = "Active",
  name = "test1",
  cardType = "physical",
  paymentSystem = "Visa"
)

val card2 = Card(
  accountId = accountId1,
  number = "5555666677778888",
  expiredAt = "2022-04-21T00:00:00Z",
  status = "Blocked",
  name = "test2",
  cardType = "virtual",
  paymentSystem = "Mastercard"
)
val card3 = Card(
  accountId = accountId1,
  number = "5555666677778888",
  expiredAt = "2022-04-21T00:00:00Z",
  status = "Active",
  name = "test2",
  cardType = "virtual",
  paymentSystem = "Mastercard"
)

val account1 = Account(
  accountId = accountId1,
  cards = listOf(card1, card2),
  number = "1234567890",
  status = "Active",
  balance = "1000.00",
  currency = "RUB"
)
val account2 = Account(
  accountId = accountId2,
  cards = listOf(card3),
  number = "1234567890",
  status = "Active",
  balance = "1000.00",
  currency = "USD"
)

val accountList = listOf(account1, account2)

val profile = Profile(
  id = "0",
  firstName = "Ivan",
  middleName = "Ivanovich",
  lastName = "Ivanov",
  country = "Russia",
  phone = "+79123213233",
  deposits = emptyList(),
  accounts = emptyList()
)


val accountsData = MutableStateFlow<List<Account>>(emptyList())
val depositsData = MutableStateFlow<List<Deposit>>(emptyList())

val dataAccountsState = MutableStateFlow<LceState>(LceState.None)
val dataDepositsState = MutableStateFlow<LceState>(LceState.None)

suspend fun fetchMoreData() {
  dataState.value = LceState.Loading
  dataAccountsState.value = LceState.Loading
  dataDepositsState.value = LceState.Loading
  delay(2.seconds)
  if (Random.nextBoolean()) {
    dataAccountsState.value = LceState.Content
    accountsData.value = accountList
  } else {
    dataState.value = LceState.Error("Failed to load data")
  }
  if (Random.nextBoolean()) {
    dataDepositsState.value = LceState.Content
    depositsData.value = depositList
  } else {
    dataState.value = LceState.Error("Failed to load data")
  }
  if (dataAccountsState.value != LceState.Content && dataDepositsState.value != LceState.Content) {
    dataState.value = LceState.Error("Failed to load data")
  } else {
    dataState.value = LceState.Content
  }
}

val dataState = MutableStateFlow<LceState>(LceState.None)
