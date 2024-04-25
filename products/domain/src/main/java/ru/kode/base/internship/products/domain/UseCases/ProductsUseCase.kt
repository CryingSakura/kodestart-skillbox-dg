package ru.kode.base.internship.products.domain.UseCases

import com.squareup.anvil.annotations.MergeComponent
import dagger.Provides
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import ru.kode.base.core.di.AppScope
import ru.kode.base.core.di.SingleIn
import ru.kode.base.internship.core.domain.BaseUseCase
import ru.kode.base.internship.core.domain.entity.LceState
import ru.kode.base.internship.products.domain.repository.AccountsRepository
import ru.kode.base.internship.products.domain.repository.DepositRepository
import javax.inject.Inject

@SingleIn(AppScope::class)
class ProductsUseCase @Inject constructor(
  private val accRepository: AccountsRepository,
  private val depRepository: DepositRepository,
) : BaseUseCase<ProductsUseCase.State>(State()) {
  data class State(
    val accountsState: LceState = LceState.None,
    val depositState: LceState = LceState.None,
  )

  val accounts = accRepository.accountsFlow
  val deposits = depRepository.depositsFlow

  val accountsState: Flow<LceState>
    get() = stateFlow.map { it.accountsState }.distinctUntilChanged()


  suspend fun fetchAccounts() {
    setState { copy(depositState = LceState.Loading) }
    try {
      setState { copy(depositState = LceState.Content) }
    } catch (e: Exception) {
      setState { copy(depositState = LceState.Error(e.message)) }
    }
  }

  val depositsState: Flow<LceState>
    get() = stateFlow.map { it.depositState }.distinctUntilChanged()

  suspend fun fetchDeposits() {
    setState { copy(depositState = LceState.Loading) }
    try {
      setState { copy(depositState = LceState.Content) }
    } catch (e: Exception) {
      setState { copy(depositState = LceState.Error(e.message)) }
    }
  }
}
