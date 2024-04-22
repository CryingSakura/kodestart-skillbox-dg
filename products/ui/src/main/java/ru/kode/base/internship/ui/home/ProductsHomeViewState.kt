package ru.kode.base.internship.ui.home

import androidx.compose.runtime.Immutable
import ru.kode.base.internship.core.domain.entity.LceState

@Immutable
data class ProductsHomeViewState(
  val loadingLceState: LceState = LceState.None,
  val refreshing: Boolean = false,
  val loadingAccountsLceStates: LceState = LceState.Loading,
  val loadingDepositsLceStates: LceState = LceState.Loading,
  val deposits: List<Deposit> = emptyList(),
  val accounts: List<Account> = emptyList(),
)