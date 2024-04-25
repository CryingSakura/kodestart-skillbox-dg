package ru.kode.base.internship.ui.home

import androidx.compose.runtime.Immutable
import ru.kode.base.internship.core.domain.entity.LceState

@Immutable
data class ProductsHomeViewState(
  val loadingAccountsLceStates: LceState = LceState.None,
  val loadingDepositsLceStates: LceState = LceState.None,
  val deposits: List<Deposit> = emptyList(),
  val accounts: List<Account> = emptyList(),
)
