package ru.kode.base.internship.ui.home

import androidx.compose.runtime.Immutable
import ru.kode.base.internship.core.domain.entity.LceState
import ru.kode.base.internship.products.domain.entity.AccountDomainEntity
import ru.kode.base.internship.products.domain.entity.DepositDomainEntity

@Immutable
data class ProductsHomeViewState(
  val loadingAccountsLceStates: LceState = LceState.None,
  val loadingDepositsLceStates: LceState = LceState.None,
  val deposits: List<DepositDomainEntity> = emptyList(),
  val accounts: List<AccountDomainEntity> = emptyList(),
)
