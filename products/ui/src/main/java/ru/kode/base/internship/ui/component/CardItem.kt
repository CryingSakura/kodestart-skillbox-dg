package ru.kode.base.internship.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.kode.base.internship.products.domain.CardStatus
import ru.kode.base.internship.products.domain.CardType
import ru.kode.base.internship.products.domain.PaymentSystem
import ru.kode.base.internship.products.ui.R
import ru.kode.base.internship.ui.cardicon.CardIcon
import ru.kode.base.internship.ui.core.uikit.theme.AppTheme

@Composable
fun CardItem(
  modifier: Modifier = Modifier,
  onCardClick: () -> Unit,
  cardName: String,
  cardStatus: CardStatus,
  cardType: CardType,
  cardPaymentSystem: PaymentSystem,
  cardNumber: String,
) {
  Row(
    modifier = modifier
      .height(72.dp)
      .fillMaxWidth()
      .clickable(onClick = onCardClick),
    verticalAlignment = Alignment.CenterVertically,
  ) {
    Icon(
      modifier = Modifier.padding(24.dp),
      painter = painterResource(
        id = R.drawable.ic_input_24
      ),
      contentDescription = "input icon",
      tint = Color.Unspecified,
    )
    Column(
      horizontalAlignment = Alignment.Start,
      verticalArrangement = Arrangement.SpaceEvenly
    ) {
      Text(text = cardName, style = AppTheme.typography.body2)
      Text(
        text = when (cardStatus) {
          CardStatus.Blocked -> cardStatus.toString()
          CardStatus.Active -> cardType.toString()
        },
        color = when (cardStatus) {
          CardStatus.Blocked -> AppTheme.colors.indicatorContendError
          CardStatus.Active -> AppTheme.colors.textSecondary
        },
        style = AppTheme.typography.caption1
      )
    }
    Spacer(modifier = Modifier.weight(1f))

    CardIcon(
      modifier = Modifier.padding(end = 16.dp),
      lastFourDigits = cardNumber.takeLast(4),
      paymentSystem = cardPaymentSystem
    )
  }
}
