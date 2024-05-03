package ru.kode.base.internship.ui.carddetails.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.kode.base.internship.products.domain.CardStatus
import ru.kode.base.internship.products.domain.CardType
import ru.kode.base.internship.products.domain.Money
import ru.kode.base.internship.products.domain.PaymentSystem
import ru.kode.base.internship.products.ui.R
import ru.kode.base.internship.ui.core.uikit.theme.AppTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun CardIcon(
  modifier: Modifier = Modifier,
  paymentSystem: PaymentSystem,
  money: Money,
  name: String,
  cardType: CardType,
  lastFourDigits: String,
  closeDate: String,
  cardStatus: CardStatus,
) {
  Card(
    modifier = modifier
      .size(height = 160.dp, width = 272.dp),
    shape = RoundedCornerShape(20.dp)
  ) {
    Image(
      modifier = Modifier.fillMaxSize(),
      painter = painterResource(id = R.drawable.ic_bankcard_40x28),
      contentDescription = "Card background",
      contentScale = ContentScale.FillBounds
    )
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 12.dp)) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
          painter = painterResource(
            id = when (paymentSystem) {
              PaymentSystem.Visa -> R.drawable.ic_visa_32x24
              PaymentSystem.Mastercard -> R.drawable.ic_mastercard_32x24
            }
          ),
          contentDescription = null,
          tint = Color.Unspecified
        )
        Column(
          modifier = Modifier.padding(start = 16.dp),
          horizontalAlignment = Alignment.Start,
          verticalArrangement = Arrangement.SpaceEvenly
        ) {
          Text(
            text = name,
            color = Color.White,
            style = AppTheme.typography.body2
          )
          Text(
            text = when (cardType) {
              CardType.physical -> "физическая"
              CardType.virtual -> "виртуальная"
            },
            color = Color.White,
            style = AppTheme.typography.caption2
          )
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(painter = painterResource(id = R.drawable.ic_nfc_24x24), contentDescription = null, tint = Color.White)
      }
      Text(
        modifier = Modifier.padding(top = 24.dp),
        text = when (cardStatus) {
          CardStatus.ACTIVE -> money.format()
          CardStatus.DEACTIVATED -> {
            stringResource(id = R.string.blocked_card)
          }
        },
        color = when (cardStatus) {
          CardStatus.DEACTIVATED -> AppTheme.colors.indicatorContendError
          CardStatus.ACTIVE -> AppTheme.colors.textButton
        }
      )
      Row(modifier = Modifier.padding(top = 28.dp)) {
        Text(text = "**** $lastFourDigits", color = Color.White)
        Spacer(modifier = Modifier.weight(1f))
        Text(
          text = parseToMMYY(closeDate),
          color = Color.White,
        )
      }
    }
  }
}

fun parseToMMYY(dateString: String): String {
  val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
  val parsedDateTime = LocalDateTime.parse(dateString, formatter)
  return parsedDateTime.format(DateTimeFormatter.ofPattern("MM/yy"))
}
