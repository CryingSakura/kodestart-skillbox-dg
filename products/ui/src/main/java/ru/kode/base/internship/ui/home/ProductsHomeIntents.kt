package ru.kode.base.internship.ui.home

import ru.kode.base.core.BaseViewIntents
import ru.kode.base.internship.products.domain.entity.CardDataEntity

class ProductsHomeIntents : BaseViewIntents() {
  val navigateOnBack = intent(name = "navigateOnBack")
  val openNewAccountOrProduct = intent(name = "openNewAccountOrProduct")
  val cardDetailsRequested = intent<CardDataEntity.Id>(name = "cardDetailsRequested")
  val accountDetailsRequested = intent(name = "accountDetailsRequested")
  val depositDetailsRequested = intent(name = "depositDetailsRequested")
  val refreshData = intent(name = "refreshData")
  val refreshDeposits = intent(name = "refreshDeposit")
  val refreshAccounts = intent(name = "refreshAccount")
}
