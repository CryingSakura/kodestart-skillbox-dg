package ru.kode.base.internship.ui.carddetails

import ru.kode.base.core.BaseViewIntents

class CardDetailsIntents : BaseViewIntents() {
  val navigateOnBack = intent(name = "navigateOnBack")
  val showActions = intent(name = "showActions")
  val showPayment = intent(name = "showPayment")
  val showHistory = intent(name = "showHistory")
  val changeText = intent<String>(name = "changeText")
  val confirmRenaming = intent(name = "confirmRenaming")
  val dismissRenaming = intent(name = "dismissRenaming")
  val alertDialog = intent(name = "alertDialog")
}
