package ru.kode.base.internship.ui.home

import androidx.compose.runtime.Immutable
import ru.kode.base.internship.core.domain.entity.LceState
import ru.kode.base.internship.products.domain.entity.AccountDataEntity
import ru.kode.base.internship.products.domain.entity.DepositDataEntity

@Immutable
data class ProductsHomeViewState(
  val loadingAccountsLceStates: LceState = LceState.None,
  val loadingDepositsLceStates: LceState = LceState.None,
  val deposits: List<DepositDataEntity> = emptyList(),
  val accounts: List<AccountDataEntity> = emptyList(),
)
