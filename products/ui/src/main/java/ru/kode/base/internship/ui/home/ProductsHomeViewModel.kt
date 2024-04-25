package ru.kode.base.internship.ui.home

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import ru.dimsuz.unicorn2.Machine
import ru.dimsuz.unicorn2.machine
import ru.kode.base.core.BaseViewModel
import ru.kode.base.internship.routing.FlowEvent
import javax.inject.Inject

class ProductsHomeViewModel @Inject constructor(
  private val flowEvents: MutableSharedFlow<FlowEvent>,
) : BaseViewModel<ProductsHomeViewState, ProductsHomeIntents>() {
  override fun buildMachine(): Machine<ProductsHomeViewState> = machine {
    initial = ProductsHomeViewState() to {
      viewModelScope.launch {
        fetchDepositsData()
      }
      viewModelScope.launch {
        fetchAccountsData()
      }
    }

    onEach(intent(ProductsHomeIntents::refreshAccountData)) {
      action { _, _, _ ->
        viewModelScope.launch {
          fetchAccountsData()
        }
      }
    }
    onEach(intent(ProductsHomeIntents::refreshDepositData)) {
      action { _, _, _ ->
        viewModelScope.launch {
          fetchDepositsData()
        }
      }
    }

    onEach(intent(ProductsHomeIntents::navigateOnBack)) {
      action { _, _, _ ->
        flowEvents.tryEmit(FlowEvent.ProductHomeDismissed)
      }
    }

    onEach(intent(ProductsHomeIntents::refreshData)) {
      action { _, _, _ ->
        viewModelScope.launch {
          fetchAccountsData()
        }
        viewModelScope.launch {
          fetchDepositsData()
        }
      }
    }

    onEach(dataAccountsState) {
      transitionTo { state, dataAccountsState ->
        state.copy(loadingAccountsLceStates = dataAccountsState)
      }
    }
    onEach(dataDepositsState) {
      transitionTo { state, dataDepositsState ->
        state.copy(loadingDepositsLceStates = dataDepositsState)
      }
    }

    onEach(accountsData) {
      transitionTo { state, accountsData ->
        state.copy(accounts = accountsData)
      }
    }
    onEach(depositsData) {
      transitionTo { state, depositsData ->
        state.copy(deposits = depositsData)
      }
    }

    onEach(intent(ProductsHomeIntents::accountDetailsRequested)) {}
    onEach(intent(ProductsHomeIntents::depositDetailsRequested)) {}
    onEach(intent(ProductsHomeIntents::cardDetailsRequested)) {}
    onEach(intent(ProductsHomeIntents::openNewAccountOrProduct)) {}
  }
}
