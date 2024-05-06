package ru.kode.base.internship.products.domain

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.Locale

enum class PaymentSystem {
  Mastercard,
  Visa,
}

enum class CardType {
  physical,
  virtual
}
enum class CardStatus {
  ACTIVE,
  DEACTIVATED,
}
enum class AccountStatus {
  ACTIVE,
  DEACTIVATED,
}
enum class DepositStatus {
  ACTIVE,
  DEACTIVATED,
}

enum class Currency {
  RUB,
  USD,
  EUR
}

data class Money(
  val balance: String,
  val currency: Currency,
)