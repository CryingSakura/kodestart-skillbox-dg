package ru.kode.base.internship.ui.carddetails

import ru.kode.base.core.BaseViewIntents

class CardDetailsIntents : BaseViewIntents() {
  val navigateOnBack = intent(name = "navigateOnBack")
  val showActions = intent(name = "showActions")
  val showPayment = intent(name = "showPayment")
  val showHistory = intent(name = "showHistory")
  val changeText = intent<String>(name = "changeText")
  val confirm = intent(name = "confirm")
  val dismiss = intent(name = "dismiss")
  val alertDialog = intent(name = "alertDialog")
}
