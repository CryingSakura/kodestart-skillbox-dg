package ru.kode.base.internship.ui

import ru.kode.base.core.util.randomUuid
import ru.kode.base.internship.products.domain.Currency
import ru.kode.base.internship.products.domain.Money
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

val accountId1UI: String = randomUuid()
val cardId1UI: String = randomUuid()

internal fun parseNumber(input: String): String {
  val number = input.toDouble()
  val symbols = DecimalFormatSymbols.getInstance(Locale.getDefault())
  symbols.groupingSeparator = ' '
  val formatter = DecimalFormat("#,###", symbols)
  return formatter.format(number)
}
internal fun format(money: Money): String {
  return "${parseNumber(money.balance)} " + when (money.currency) {
    Currency.RUB -> "₽"
    Currency.USD -> "$"
    Currency.EUR -> "€"
  }
}
