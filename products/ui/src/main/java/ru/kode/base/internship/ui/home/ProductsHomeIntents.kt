package ru.kode.base.internship.ui.home

import ru.kode.base.core.BaseViewIntents

class ProductsHomeIntents : BaseViewIntents() {
  val navigateOnBack = intent(name = "navigateOnBack")
  val openNewAccountOrProduct = intent(name = "openNewAccountOrProduct")
  val cardDetailsRequested = intent(name = "cardDetailsRequested")
  val accountDetailsRequested = intent(name = "accountDetailsRequested")
  val depositDetailsRequested = intent(name = "depositDetailsRequested")
  val refreshData = intent(name = "refreshData")
  val refreshDepositData = intent(name = "refreshDepositData")
  val refreshAccountData = intent(name = "refreshAccountData")
}
