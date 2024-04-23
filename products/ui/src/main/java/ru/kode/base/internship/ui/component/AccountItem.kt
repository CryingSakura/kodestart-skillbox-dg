package ru.kode.base.internship.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.kode.base.internship.products.ui.R
import ru.kode.base.internship.ui.core.uikit.theme.AppTheme
import ru.kode.base.internship.ui.home.Card
import ru.kode.base.internship.ui.home.Currency
import ru.kode.base.internship.ui.home.Money

@Composable
fun AccountItem(
  modifier: Modifier = Modifier,
  onAccountItemClick: () -> Unit,
  onCardClick: () -> Unit,
  cards: List<Card>,
  unLastAccountInList: Boolean,
  money: Money,
) {
  var cardsExpanded by remember { mutableStateOf(true) }

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
        id = when (money.currency) {
          Currency.RUB -> R.drawable.ic_rub_40
          Currency.USD -> R.drawable.ic_dollar_40
          Currency.EUR -> R.drawable.ic_eu_40
        }
      ),
      contentDescription = "currency icon",
      tint = Color.Unspecified
    )
    Column(
      horizontalAlignment = Alignment.Start,
      verticalArrangement = Arrangement.SpaceEvenly
    ) {
      Text(text = stringResource(id = R.string.account), style = AppTheme.typography.body2)
      Text(
        text = money.format(),
        style = AppTheme.typography.body2,
        color = AppTheme.colors.contendAccentPrimary
      )
    }
    Spacer(modifier = Modifier.weight(1f))
    if (cards.isNotEmpty()) {
      IconButton(
        modifier = Modifier.padding(end = 12.dp),
        onClick = { cardsExpanded = !cardsExpanded },
      ) {
        Icon(
          painter = painterResource(
            id = if (cardsExpanded)
              R.drawable.ic_button_shows_40x28
            else
              R.drawable.ic_button_hides_40x28
          ),
          contentDescription = "test",
          tint = Color.Unspecified
        )
      }
    }
  }
  if (cardsExpanded && cards.isNotEmpty()) {
    Column(modifier = modifier) {
      for (card in cards) {
        CardItem(
          onCardClick = onCardClick,
          cardName = card.name,
          cardPaymentSystem = card.paymentSystem,
          cardStatus = card.status,
          cardType = card.cardType,
          cardNumber = card.number,
        )
        if (card != cards.last()) {
          Divider(
            modifier = Modifier.padding(start = 72.dp, end = 16.dp),
            color = AppTheme.colors.contendSecondary,
            thickness = 2.dp
          )
        }
      }
    }
  }
  if (unLastAccountInList && !cardsExpanded) {
    Divider(
      modifier = Modifier.padding(start = 72.dp, end = 16.dp),
      color = AppTheme.colors.contendSecondary,
      thickness = 2.dp
    )
  }
}
