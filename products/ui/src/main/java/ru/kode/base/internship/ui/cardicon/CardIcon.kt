package ru.kode.base.internship.ui.cardicon

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.kode.base.internship.products.domain.PaymentSystem
import ru.kode.base.internship.products.ui.R
import ru.kode.base.internship.ui.core.uikit.theme.AppTheme

@Composable
fun CardIcon(
  modifier: Modifier = Modifier,
  lastFourDigits: String,
  paymentSystem: PaymentSystem,
) {
  Box(
    modifier = modifier
      .size(height = 28.dp, width = 40.dp),
    contentAlignment = Alignment.TopEnd
  ) {
    Icon(
      modifier = Modifier.fillMaxSize(),
      painter = painterResource(id = R.drawable.ic_bankcard_40x28),
      contentDescription = "Card background",
      tint = Color.Unspecified
    )

    Text(
      modifier = Modifier
        .padding(end = 4.dp, top = 4.dp)
        .fillMaxWidth()
        .heightIn(min = 12.dp),
      style = AppTheme.typography.body1,
      maxLines = 1,
      text = lastFourDigits,
      color = Color.White,
      fontSize = 10.sp,
      textAlign = TextAlign.End
    )

    when (paymentSystem) {
      PaymentSystem.Mastercard -> Icon(
        modifier = Modifier
          .padding(start = 19.dp, top = 15.dp, end = 5.dp)
          .size(height = 16.dp, width = 12.dp),
        painter = painterResource(id = R.drawable.ic_mastercard_16x12),
        contentDescription = "Mastercard",
        tint = Color.Unspecified
      )

      PaymentSystem.Visa -> Icon(
        modifier = Modifier
          .padding(start = 19.dp, top = 15.dp, end = 5.dp)
          .size(height = 16.dp, width = 12.dp),
        painter = painterResource(id = R.drawable.ic_visa_16x12),
        contentDescription = "Visa",
        tint = Color.Unspecified
      )
    }
  }
}

@Composable
@Preview(showBackground = true)
private fun CardIconPreview() {
  AppTheme {
    CardIcon(
      lastFourDigits = "7789",
      paymentSystem = PaymentSystem.valueOf("Visa")
    )
  }
}
