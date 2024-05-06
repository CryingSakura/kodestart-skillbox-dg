package ru.kode.base.internship.ui.carddetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
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
import ru.kode.base.internship.ui.carddetails.components.RenameDialog
import ru.kode.base.internship.ui.carddetails.states.ListState
import ru.kode.base.internship.ui.core.uikit.screen.AppScreen
import ru.kode.base.internship.ui.core.uikit.theme.AppTheme

@Composable
fun CardDetailsScreen(viewModel: CardDetailsViewModel = daggerViewModel()) = AppScreen(
  viewModel = viewModel,
  intents = rememberViewIntents()
) { state, intents ->
  OnBackPressedHandler(onBack = intents.navigateOnBack)
  if (state.showDialog) {
    RenameDialog(
      value = state.enterName,
      onValueChanged = intents.changeText,
      onConfirmRequest = intents.confirmRenaming,
      onDismissRequest = intents.dismissRenaming
    )
  }
  Box(
    modifier = Modifier
      .statusBarsPadding()
      .navigationBarsPadding()
      .imePadding()
      .background(AppTheme.colors.backgroundPrimary),
  ) {
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
            money = state.money,
            cardType = state.cardDetails.cardType,
            lastFourDigits = state.cardDetails.number.takeLast(4),
            closeDate = state.cardDetails.expiredAt,
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
      item { Spacer(modifier = Modifier.height(16.dp)) }
      when (state.listState) {
        ListState.Actions -> items(itemsColumnList) { itemData ->
          Column(Modifier.background(color = AppTheme.colors.backgroundSecondary)) {
            itemData.onEach {
              CardDetailsListItem(
                modifier = Modifier.fillMaxWidth(),
                icon = it.key,
                text = it.value,
                onClick = intents.alertDialog
              )
              if (itemData != itemsColumnList.last()) {
                Divider(
                  modifier = Modifier.padding(start = 56.dp, end = 16.dp),
                  color = AppTheme.colors.contendSecondary,
                  thickness = 2.dp
                )
              }
            }
          }
        }
        ListState.Payments -> {}
        ListState.History -> {}
      }
    }
  }
}

val itemsColumnList = listOf(
  mapOf(R.drawable.ic_rename_card_24x24 to R.string.rename_card),
  mapOf(R.drawable.ic_account_details_24x24 to R.string.account_details),
  mapOf(R.drawable.ic_block_card_24x24 to R.string.block_card),
  mapOf(R.drawable.ic_card_info_24x24 to R.string.card_information),
  mapOf(R.drawable.ic_issue_card_24x24 to R.string.issue_card)
)
