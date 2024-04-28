package ru.kode.base.internship.products.domain.UseCases

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import ru.kode.base.core.di.AppScope
import ru.kode.base.core.di.SingleIn
import ru.kode.base.internship.core.domain.BaseUseCase
import ru.kode.base.internship.core.domain.entity.LceState
import ru.kode.base.internship.products.domain.entity.CardDataEntity
import ru.kode.base.internship.products.domain.repository.AccountsRepository
import ru.kode.base.internship.products.domain.repository.CardRepository
import ru.kode.base.internship.products.domain.repository.DepositRepository
import javax.inject.Inject

@SingleIn(AppScope::class)
class ProductsUseCase @Inject constructor(
  private val accRepository: AccountsRepository,
  private val depRepository: DepositRepository,
  private val cardRepository: CardRepository,
) : BaseUseCase<ProductsUseCase.State>(State()) {
  data class State(
    val accountsState: LceState = LceState.None,
    val depositState: LceState = LceState.None,
    val cardState: LceState = LceState.None,
  )
  val deposits = depRepository.depositsFlow
  val accounts = accRepository.accountsFlow
  val cardDetails = cardRepository.card
  val money = cardRepository.money
  val accountsState: Flow<LceState>
    get() = stateFlow.map { it.accountsState }.distinctUntilChanged()
  suspend fun fetchAccounts() {
    setState { copy(accountsState = LceState.Loading) }
    delay(2000)
    try {
      accRepository.fetchAccounts()
      setState { copy(accountsState = LceState.Content) }
    } catch (e: Exception) {
      setState { copy(accountsState = LceState.Error(e.message)) }
    }
  }
  suspend fun refresh() {
    setState {
      copy(
        accountsState = LceState.Loading,
        depositState = LceState.Loading
      )
    }
    delay(2000)
    try {
      accRepository.fetchAccounts()
      depRepository.fetchDeposits()
      setState {
        copy(
          accountsState = LceState.Content,
          depositState = LceState.Content
        )
      }
      setState { copy(depositState = LceState.Content) }
    } catch (e: Exception) {
      setState {
        copy(
          accountsState = LceState.Error(e.message),
          depositState = LceState.Error(e.message)
        )
      }
    }
  }
  val depositsState: Flow<LceState>
    get() = stateFlow.map { it.depositState }.distinctUntilChanged()
  suspend fun fetchDeposits() {
    setState { copy(depositState = LceState.Loading) }
    delay(2000)
    try {
      depRepository.fetchDeposits()
      setState { copy(depositState = LceState.Content) }
    } catch (e: Exception) {
      setState { copy(depositState = LceState.Error(e.message)) }
    }
  }
  suspend fun loadAll() {
    setState {
      copy(
        accountsState = LceState.Loading,
        depositState = LceState.Loading
      )
    }
    delay(2000)
    try {
      depRepository.fetchDeposits()
      accRepository.fetchAccounts()
      setState {
        copy(
          accountsState = LceState.Content,
          depositState = LceState.Content
        )
      }
    } catch (e: Exception) {
      setState {
        copy(
          accountsState = LceState.Error(e.message),
          depositState = LceState.Error(e.message)
        )
      }
    }
  }

  /*  val cardState: Flow<LceState>
      get() = stateFlow.map { it.cardState }.distinctUntilChanged()*/
  suspend fun cardDetails(id: CardDataEntity.Id) {
    setState { copy(cardState = LceState.Loading) }
    try {
      cardRepository.cardDetails(id)
      cardRepository.getMoney(cardRepository.card.value.accountId)
      setState { copy(cardState = LceState.Content) }
    } catch (e: Exception) {
      setState { copy(cardState = LceState.Error(e.message)) }
    }
  }
  fun rename(newName: String, id: CardDataEntity.Id) {
    cardRepository.rename(newName, id)
  }
}
