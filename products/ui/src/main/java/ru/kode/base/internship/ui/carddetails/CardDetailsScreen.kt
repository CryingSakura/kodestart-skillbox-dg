package ru.kode.base.internship.ui.carddetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.kode.base.core.compose.OnBackPressedHandler
import ru.kode.base.core.rememberViewIntents
import ru.kode.base.core.viewmodel.daggerViewModel
import ru.kode.base.internship.products.ui.R
import ru.kode.base.internship.ui.carddetails.components.ActionRow
import ru.kode.base.internship.ui.carddetails.components.Bar
import ru.kode.base.internship.ui.carddetails.components.CardDetailsListItem
import ru.kode.base.internship.ui.carddetails.components.CardIcon
import ru.kode.base.internship.ui.carddetails.states.ListState
import ru.kode.base.internship.ui.core.uikit.screen.AppScreen
import ru.kode.base.internship.ui.core.uikit.theme.AppTheme

@Composable
fun CardDetailsScreen(viewModel: CardDetailsViewModel = daggerViewModel()) = AppScreen(
  viewModel = viewModel,
  intents = rememberViewIntents()
) { state, intents ->
  OnBackPressedHandler(onBack = intents.navigateOnBack)
  Box(
    modifier = Modifier
      .statusBarsPadding()
      .navigationBarsPadding()
      .imePadding()
      .background(AppTheme.colors.backgroundPrimary),
  ) {
/*
    if (state.showDialog){
      ChangeNameDialog(value = state.enterName, onValueChanged = intents.changeText, onConfirmRequest = intents.confirm, onDismissRequest = intents.dismiss)
    }*/

    LazyColumn(modifier = Modifier.fillMaxSize()) {
      item {
        Bar(
          modifier = Modifier
            .fillMaxWidth(),
          onClick = intents.navigateOnBack
        )
      }
      item {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.Center
        ) {
          CardIcon(
            modifier = Modifier.padding(top = 16.dp),
            name = state.cardDetails.name,
            paymentSystem = state.cardDetails.paymentSystem,
            balance = "77777 ₽",
            cardType = state.cardDetails.cardType,
            lastFourDigits = state.cardDetails.number.takeLast(4),
            closeDate = state.cardDetails.expiredAt.take(4),
            cardStatus = state.cardDetails.status
          )
        }
      }
      item {
        ActionRow(
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
          onActionsClick = intents.showActions,
          onHistoryClick = intents.showHistory,
          onPaymentsClick = intents.showPayment,
          state = state.listState
        )
      }
      when (state.listState) {
        ListState.Actions -> items(itemsColumnList) { itemData ->
          itemData.onEach {
            CardDetailsListItem(
              modifier = Modifier.fillMaxWidth(),
              icon = it.key,
              text = it.value,
              onClick = intents.alertDialog
            )
          }
        }
        ListState.Payments -> {}
        ListState.History -> {}
      }
    }
  }
}

val itemsColumnList = listOf(
  mapOf(R.drawable.ic_rename_card_24x24 to "Переименовать карту"),
  mapOf(R.drawable.ic_account_details_24x24 to "Реквизиты счета"),
  mapOf(R.drawable.ic_block_card_24x24 to "Заблокировать карту"),
  mapOf(R.drawable.ic_card_info_24x24 to "Информация о карте"),
  mapOf(R.drawable.ic_issue_card_24x24 to "Выпустить карту")
)
