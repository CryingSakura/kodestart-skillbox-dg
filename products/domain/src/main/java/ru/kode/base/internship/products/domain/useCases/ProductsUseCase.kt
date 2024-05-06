package ru.kode.base.internship.products.domain.useCases

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.kode.base.core.di.AppScope
import ru.kode.base.core.di.SingleIn
import ru.kode.base.internship.core.domain.BaseUseCase
import ru.kode.base.internship.core.domain.entity.LceState
import ru.kode.base.internship.products.domain.Currency
import ru.kode.base.internship.products.domain.Money
import ru.kode.base.internship.products.domain.entity.AccountDomainEntity
import ru.kode.base.internship.products.domain.entity.CardDomainEntity
import ru.kode.base.internship.products.domain.entity.DepositDomainEntity
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
    val cardDetailsId: CardDomainEntity.Id = CardDomainEntity.Id("0"),
  )

  val deposits = depRepository.getAllDeposits()
  private val cards = cardRepository.getAllCards()
  val money = MutableStateFlow(
    Money("0", Currency.RUB)
  )
  val accounts: Flow<List<AccountDomainEntity>> = accRepository.getAllAccounts()
  val accountsState: Flow<LceState>
    get() = stateFlow.map { it.accountsState }.distinctUntilChanged()


  val cardDetails: Flow<CardDomainEntity> = stateFlow
    .mapNotNull { it.cardDetailsId }
    .flatMapLatest { cardRepository.getCardById(it) }
    .filterNotNull()

  suspend fun fetchAccounts() {
    setState { copy(accountsState = LceState.Loading) }
    try {
      accRepository.fetchAccounts()
      setState { copy(accountsState = LceState.Content) }
    } catch (e: Exception) {
      setState { copy(accountsState = LceState.Error(e.message)) }
    }
  }

  val depositsState: Flow<LceState>
    get() = stateFlow.map { it.depositState }.distinctUntilChanged()

  suspend fun fetchDeposits() {
    setState { copy(depositState = LceState.Loading) }
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
    try {
      accRepository.fetchAccounts()
      depRepository.fetchDeposits()
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

  suspend fun cardDetails(id: CardDomainEntity.Id) {
    setState { copy(cardState = LceState.Loading) }
    try {
      CoroutineScope(Dispatchers.Default).launch {
        cards.collectLatest { cardList ->
          val card = cardList.find { it.cardId == id }!!
          accounts.collectLatest { accList ->
            money.update {
              Money(
                accList.find { it.accountId == card.accountId }!!.balance,
                accList.find { it.accountId == card.accountId }!!.currency
              )
            }
          }
        }
      }
      setState {
        copy(cardDetailsId = id)
      }
      setState { copy(cardState = LceState.Content) }
    } catch (e: Exception) {
      setState { copy(cardState = LceState.Error(e.message)) }
    }
  }

}
