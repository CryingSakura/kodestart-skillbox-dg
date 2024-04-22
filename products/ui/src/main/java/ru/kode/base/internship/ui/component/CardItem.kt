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
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.kode.base.internship.products.ui.R
import ru.kode.base.internship.ui.cardicon.CardIcon
import ru.kode.base.internship.ui.core.uikit.theme.AppTheme

@Composable
fun CardItem(
  modifier: Modifier = Modifier,
  onCardClick: () -> Unit,
  cardName: String,
  cardStatus: String,
  cardType: String,
  cardPaymentSystem: String,
  cardNumber: String,
) {
  Row(
    modifier = modifier
      .heightIn(min = 72.dp)
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
      Text(text = cardName, fontSize = 15.sp)
      Text(
        text = if (cardStatus == "Blocked") cardStatus
        else cardType,
        fontSize = 13.sp,
        color = if (cardStatus == "Blocked") AppTheme.colors.indicatorContendError
        else AppTheme.colors.textSecondary
      )

    }
    Spacer(modifier = Modifier.weight(1f))

    CardIcon(lastFourDigits = cardNumber.takeLast(4), paymentSystem = cardPaymentSystem)

    Spacer(modifier = Modifier.width(16.dp))
  }
}

