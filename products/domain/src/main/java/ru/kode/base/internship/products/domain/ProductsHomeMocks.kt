package ru.kode.base.internship.products.domain

enum class PaymentSystem {
  Mastercard,
  Visa,
}

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
  val currency: Currency,
) {
  fun format(): String {
    return "$balance " + when (currency) {
      Currency.RUB -> "₽"
      Currency.USD -> "$"
      Currency.EUR -> "€"
    }
  }
}
