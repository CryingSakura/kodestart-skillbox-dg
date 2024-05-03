package ru.kode.base.internship.ui.carddetails

import kotlinx.coroutines.flow.MutableSharedFlow
import ru.dimsuz.unicorn2.Machine
import ru.dimsuz.unicorn2.machine
import ru.kode.base.core.BaseViewModel
import ru.kode.base.internship.products.domain.UseCases.ProductsUseCase
import ru.kode.base.internship.routing.FlowEvent
import ru.kode.base.internship.ui.carddetails.states.ListState
import javax.inject.Inject

class CardDetailsViewModel @Inject constructor(
  private val prodUseCase: ProductsUseCase,
  private val flowEvents: MutableSharedFlow<FlowEvent>,
) : BaseViewModel<CardDetailsViewState, CardDetailsIntents>() {
  override fun buildMachine(): Machine<CardDetailsViewState> = machine {
    initial = CardDetailsViewState() to null
    onEach(intent(CardDetailsIntents::navigateOnBack)) {
      action { _, _, _ ->
        flowEvents.tryEmit(FlowEvent.ScreenDetailsDismissed)
      }
    }

    onEach(prodUseCase.cardDetails) {
      transitionTo { state, details ->
        state.copy(cardDetails = details)
      }
    }
    onEach(intent(CardDetailsIntents::showActions)) {
      transitionTo { state, _ ->
        state.copy(listState = ListState.Actions)
      }
    }
    onEach(intent(CardDetailsIntents::showHistory)) {
      transitionTo { state, _ ->
        state.copy(listState = ListState.History)
      }
    }
    onEach(intent(CardDetailsIntents::showPayment)) {
      transitionTo { state, _ ->
        state.copy(listState = ListState.Payments)
      }
    }
    onEach(intent(CardDetailsIntents::changeText)) {
      transitionTo { state, text ->
        state.copy(enterName = text)
      }
    }
    onEach(intent(CardDetailsIntents::alertDialog)) {
      transitionTo { state, _ ->
        state.copy(showDialog = true)
      }
    }
    onEach(intent(CardDetailsIntents::confirm)) {
      action { _, newState, _ ->
        prodUseCase.rename(newState.enterName, newState.cardDetails.cardId)
      }
      transitionTo { state, _ ->
        state.copy(enterName = "")
      }
      transitionTo { state, _ ->
        state.copy(showDialog = false)
      }
    }

    onEach(prodUseCase.money) {
      transitionTo { state, balance ->
        state.copy(money = balance)
      }
    }

    onEach(intent(CardDetailsIntents::dismiss)) {
      transitionTo { state, _ ->
        state.copy(showDialog = false)
      }
    }
  }
}
