package ru.kode.base.internship.products.domain.UseCases

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
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
  )

  private val _deposits = MutableStateFlow<List<DepositDomainEntity>>(emptyList())
  val deposits = _deposits.asStateFlow()
  private val _accounts = MutableStateFlow<List<AccountDomainEntity>>(emptyList())
  val accounts = _accounts.asStateFlow()
  private val _cards = MutableStateFlow<List<CardDomainEntity>>(emptyList())
  val cards = _cards.asStateFlow()
  val money = MutableStateFlow(
    Money("", Currency.RUB)
  )
  val accountsState: Flow<LceState>
    get() = stateFlow.map { it.accountsState }.distinctUntilChanged()

  private val _cardDetails = MutableStateFlow<CardDomainEntity?>(null)
  val cardDetails = _cardDetails.asStateFlow()
  suspend fun fetchAccounts() {
    setState { copy(accountsState = LceState.Loading) }
    try {
      setState { copy(accountsState = LceState.Content) }
      accRepository.fetchAccounts()
      accRepository.getAllAccounts().collectLatest { accounts ->
        _accounts.update { accounts }
      }
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
    try {
      accRepository.fetchAccounts()
      depRepository.fetchDeposits()
      cardRepository.getAllCards().collectLatest { cards ->
        _cards.update { cards }
      }
      depRepository.getAllDeposits().collectLatest { deposits ->
        _deposits.update { deposits }
      }
      accRepository.getAllAccounts().collectLatest { accounts ->
        _accounts.update { accounts }
      }
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

  val depositsState: Flow<LceState>
    get() = stateFlow.map { it.depositState }.distinctUntilChanged()

  suspend fun fetchDeposits() {
    setState { copy(depositState = LceState.Loading) }
    try {
      setState { copy(depositState = LceState.Content) }
      depRepository.fetchDeposits()
      depRepository.getAllDeposits().collectLatest { deposits ->
        _deposits.update { deposits }
      }
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
      CoroutineScope(Dispatchers.Default).launch {
        cardRepository.getAllCards().collectLatest { cards ->
          _cards.update { cards }
        }
      }
      CoroutineScope(Dispatchers.Default).launch {
        accRepository.getAllAccounts().collectLatest { accounts ->
          _accounts.update { accounts }
        }
      }
      CoroutineScope(Dispatchers.Default).launch {
        depRepository.getAllDeposits().collectLatest { deposits ->
          _deposits.update { deposits }
        }
      }
      CoroutineScope(Dispatchers.Default).launch {
        val cards = _cards.value
        _accounts.update { accounts ->
          accounts.map { it.copy(cards = cards) }
        }
      }
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
      _cardDetails.update { cardRepository.getCardById(id) }
      val card = cards.value.find { it.cardId == id }!!
      money.update {
        Money(
          accounts.value.find { it.accountId == card.accountId }!!.balance,
          accounts.value.find { it.accountId == card.accountId }!!.currency
        )
      }
      setState { copy(cardState = LceState.Content) }
    } catch (e: Exception) {
      setState { copy(cardState = LceState.Error(e.message)) }
    }
  }

  fun rename(newName: String, id: CardDomainEntity.Id) {
    cardRepository.rename(newName, id)
  }
}
