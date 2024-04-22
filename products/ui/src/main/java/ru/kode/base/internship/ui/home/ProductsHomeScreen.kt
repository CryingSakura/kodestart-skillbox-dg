package ru.kode.base.internship.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.kode.base.core.compose.OnBackPressedHandler
import ru.kode.base.core.rememberViewIntents
import ru.kode.base.core.viewmodel.daggerViewModel
import ru.kode.base.internship.core.domain.entity.LceState
import ru.kode.base.internship.products.ui.R
import ru.kode.base.internship.ui.component.AccountItem
import ru.kode.base.internship.ui.component.DepositItem
import ru.kode.base.internship.ui.core.uikit.screen.AppScreen
import ru.kode.base.internship.ui.core.uikit.theme.AppTheme
import ru.kode.base.internship.ui.error.ProductsHomeErrorScreen
import ru.kode.base.internship.ui.error.SomethingWrongComponent
import ru.kode.base.internship.ui.shimmer.ShimmerScreen
import ru.kode.base.internship.ui.shimmer.shimmerLoadingAnimation

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductsHomeScreen(viewModel: ProductsHomeViewModel = daggerViewModel()) = AppScreen(
  viewModel = viewModel, intents = rememberViewIntents()
) { state, intents ->
  OnBackPressedHandler(onBack = intents.navigateOnBack)


  val pullRefreshState = rememberPullRefreshState(
    refreshing = state.loadingLceState is LceState.Loading,
    onRefresh = intents.refreshData

  )

  Box(
    modifier = Modifier
      .statusBarsPadding()
      .navigationBarsPadding()
      .imePadding()
      .pullRefresh(pullRefreshState)
      .background(AppTheme.colors.backgroundPrimary),
  ) {
    if (state.loadingLceState is LceState.Loading) {
      Column {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(start = 16.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(
            modifier = Modifier
              .size(width = 160.dp, height = 32.dp)
              .clip(shape = RoundedCornerShape(16.dp))
          )
          {
            Box(
              modifier = Modifier
                .shimmerLoadingAnimation()
                .fillMaxSize()
            )
          }
        }
        ShimmerScreen(
          modifier = Modifier
            .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        ShimmerScreen(
          modifier = Modifier
            .fillMaxWidth()
        )
      }
    }

    if (state.loadingLceState is LceState.Content) {
      LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
          Text(
            modifier = Modifier
              .fillMaxWidth()
              .height(32.dp),
            text = stringResource(id = R.string.home),
            fontSize = 17.sp,
            textAlign = TextAlign.Center
          )
        }
        //Счета
        item {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .background(AppTheme.colors.backgroundSecondary)
          ) {
            Text(
              modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
              text = stringResource(id = R.string.accounts),
              textAlign = TextAlign.Start
            )
          }

        }
        //Список счетов
        if (state.loadingAccountsLceStates is LceState.Content) {
          if (state.accounts.isNotEmpty()) {
            items(state.accounts) { account ->
              AccountItem(
                modifier = Modifier.background(AppTheme.colors.backgroundSecondary),
                onAccountItemClick = intents.checkAccountDetails,
                cardsList = account.cards,
                onCardClick = intents.checkCardDetails,
                accountBalance = account.balance,
                unLastAccountInList = account != state.accounts.last(),
                accountCurrency = account.currency
              )
            }
          }
        } else {
          item {
            ShimmerScreen(
              modifier = Modifier
                .fillMaxWidth()
            )
            SomethingWrongComponent(onClick = intents.refreshData)
          }
        }
        item {
          Spacer(modifier = Modifier.height(16.dp))
        }

        //Вклады
        item {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .background(AppTheme.colors.backgroundSecondary)
          ) {
            Text(
              modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
              text = stringResource(id = R.string.сontribution),
              textAlign = TextAlign.Start
            )
          }
        }
        //Список вкладов
        if (state.loadingDepositsLceStates is LceState.Content) {
          if (state.deposits.isNotEmpty()) {
            items(state.deposits) { depAcc ->
              Column(Modifier.background(color = AppTheme.colors.backgroundSecondary)){
                DepositItem(
                  modifier = Modifier.background(AppTheme.colors.backgroundSecondary),
                  onDepositClick = intents.checkDepositDetails,
                  depositBalance = depAcc.balance,
                  depositCloseDate = depAcc.closeDate,
                  depositCurrency = depAcc.currency,
                  depositName = depAcc.name,
                  depositRate = depAcc.rate,
                )
                if (depAcc != state.deposits.last()) {
                  Divider(
                    modifier = Modifier.padding(start = 72.dp, end = 16.dp),
                    color = AppTheme.colors.contendSecondary,
                    thickness = 2.dp
                  )
                }
              }

            }
          }
        } else {
          item {
            ShimmerScreen(
              modifier = Modifier
                .fillMaxWidth()
            )
            SomethingWrongComponent(onClick = intents.refreshData)
          }
        }
        //Button
        if (state.loadingAccountsLceStates is LceState.Content && state.loadingDepositsLceStates is LceState.Content) {
          item {
            Button(
              modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .heightIn(min = 52.dp),
              onClick = { intents.openNewAccountOrProduct },
              colors = ButtonDefaults.buttonColors(
                backgroundColor = AppTheme.colors.primaryButton,
                contentColor = Color.White
              ),
              content = {
                Text(
                  text = stringResource(id = R.string.open_new_account_or_product),
                  fontSize = 15.sp
                )
              },
              shape = RoundedCornerShape(32.dp),
            )
          }
        }
      }
    }
    if (state.loadingLceState == LceState.Error("Failed to load data")) {
      ProductsHomeErrorScreen(
        onRefreshClick = intents.refreshData,
        onCloseClick = intents.navigateOnBack,
        refreshing = state.loadingLceState == LceState.Loading
      )
    }
    PullRefreshIndicator(
      modifier = Modifier.align(Alignment.TopCenter),
      refreshing = state.loadingLceState is LceState.Loading,
      state = pullRefreshState,
      backgroundColor = AppTheme.colors.contendAccentPrimary,
      contentColor = AppTheme.colors.contendAccentTertiary
    )
  }
}
