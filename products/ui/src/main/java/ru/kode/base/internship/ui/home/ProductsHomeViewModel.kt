package ru.kode.base.internship.ui.home

import kotlinx.coroutines.flow.MutableSharedFlow
import ru.dimsuz.unicorn2.Machine
import ru.dimsuz.unicorn2.machine
import ru.kode.base.core.BaseViewModel
import ru.kode.base.internship.products.domain.UseCases.ProductsUseCase
import ru.kode.base.internship.routing.FlowEvent
import javax.inject.Inject

class ProductsHomeViewModel @Inject constructor(
  private val prodUseCase: ProductsUseCase,
  private val flowEvents: MutableSharedFlow<FlowEvent>,
) : BaseViewModel<ProductsHomeViewState, ProductsHomeIntents>() {
  override fun buildMachine(): Machine<ProductsHomeViewState> = machine {
    initial = ProductsHomeViewState() to {
      executeAsync {
        prodUseCase.loadAll()
      }
    }

    onEach(intent(ProductsHomeIntents::refreshAccounts)) {
      action { _, _, _ ->
        prodUseCase.fetchAccounts()
      }
    }
    onEach(intent(ProductsHomeIntents::refreshDeposits)) {
      action { _, _, _ ->
        prodUseCase.fetchDeposits()
      }
    }

    onEach(intent(ProductsHomeIntents::navigateOnBack)) {
      action { _, _, _ ->
        flowEvents.tryEmit(FlowEvent.ProductHomeDismissed)
      }
    }

    onEach(intent(ProductsHomeIntents::refreshData)) {
      action { _, _, _ ->
        executeAsync {
          prodUseCase.loadAll()
        }
      }
    }

    onEach(prodUseCase.accountsState) {
      transitionTo { state, dataAccountsState ->
        state.copy(loadingAccountsLceStates = dataAccountsState)
      }
    }
    onEach(prodUseCase.depositsState) {
      transitionTo { state, dataDepositsState ->
        state.copy(loadingDepositsLceStates = dataDepositsState)
      }
    }

    onEach(prodUseCase.accounts) {
      transitionTo { state, accounts ->
        state.copy(accounts = accounts)
      }
    }

    onEach(prodUseCase.deposits) {
      transitionTo { state, deposits ->
        state.copy(deposits = deposits)
      }
    }

    onEach(intent(ProductsHomeIntents::accountDetailsRequested)) {}
    onEach(intent(ProductsHomeIntents::depositDetailsRequested)) {}

    onEach(intent(ProductsHomeIntents::cardDetailsRequested)) {
      action { _, _, cardId ->
        prodUseCase.cardDetails(cardId)
        flowEvents.tryEmit(FlowEvent.MoveOnCardDetails)
      }
    }
    onEach(intent(ProductsHomeIntents::openNewAccountOrProduct)) {}
  }
}
