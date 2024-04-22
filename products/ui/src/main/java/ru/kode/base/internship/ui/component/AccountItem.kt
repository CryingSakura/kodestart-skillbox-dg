package ru.kode.base.internship.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.kode.base.internship.products.ui.R
import ru.kode.base.internship.ui.core.uikit.theme.AppTheme
import ru.kode.base.internship.ui.home.Card

@Composable
fun AccountItem(
  modifier: Modifier = Modifier,
  onAccountItemClick: () -> Unit,
  onCardClick: () -> Unit,
  accountBalance: String,
  cardsList: List<Card>,
  unLastAccountInList: Boolean,
  accountCurrency: String,
) {
  val visibleCards = remember {
    mutableStateOf(true)
  }

  Row(
    modifier = modifier
      .heightIn(min = 72.dp)
      .fillMaxWidth()
      .clickable(onClick = onAccountItemClick),
    verticalAlignment = Alignment.CenterVertically,
  ) {
    Icon(
      modifier = Modifier.padding(16.dp),
      painter = painterResource(
        id = when (accountCurrency) {
          "RUB" -> R.drawable.ic_rub_40
          "USD" -> R.drawable.ic_dollar_40
          else -> R.drawable.ic_eu_40
        }
      ),
      contentDescription = "currency icon",
      tint = Color.Unspecified
    )
    Column(
      horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.SpaceEvenly
    ) {
      Text(text = stringResource(id = R.string.account), fontSize = 15.sp)
      Text(
        text = "$accountBalance " + when (accountCurrency) {
          "RUB" -> "₽"
          "USD" -> "$"
          else -> "€"
        },
        fontSize = 15.sp,
        color = AppTheme.colors.contendAccentPrimary
      )
    }
    Spacer(modifier = Modifier.weight(1f))
    if (cardsList.isNotEmpty()) {
      IconButton(
        onClick = { visibleCards.value = !visibleCards.value },
      ) {
        Icon(
          painter = painterResource(
            id = if (visibleCards.value) R.drawable.ic_button_shows_40x28
            else R.drawable.ic_button_hides_40x28
          ), contentDescription = "test", tint = Color.Unspecified
        )
      }
    }
    Spacer(modifier = Modifier.width(12.dp))
  }
  if (visibleCards.value && cardsList.isNotEmpty()) {
    Column(modifier = modifier) {
      for (card in cardsList) {
        CardItem(
          onCardClick = onCardClick,
          cardName = card.name,
          cardPaymentSystem = card.paymentSystem,
          cardStatus = card.status,
          cardType = card.cardType,
          cardNumber = card.number,
        )
        if (card != cardsList.last()) {
          Divider(
            modifier = Modifier.padding(start = 72.dp, end = 16.dp),
            color = AppTheme.colors.contendSecondary, thickness = 2.dp
          )
        }
      }
    }
  }
  if (unLastAccountInList && !visibleCards.value) {
    Divider(
      modifier = Modifier.padding(start = 72.dp, end = 16.dp),
      color = AppTheme.colors.contendSecondary, thickness = 2.dp
    )
  }
}