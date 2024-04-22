package ru.kode.base.internship.ui.home

import ru.kode.base.core.BaseViewIntents

class ProductsHomeIntents : BaseViewIntents() {
  val navigateOnBack = intent(name = "navigateOnBack")
  val openNewAccountOrProduct = intent(name = "openNewAccountOrProduct")
  val checkCardDetails = intent(name = "checkCardDetails")
  val checkAccountDetails = intent(name = "checkAccountDetails")
  val checkDepositDetails = intent(name = "checkContributionDetails")
  val refreshData = intent(name = "refreshData")
}
