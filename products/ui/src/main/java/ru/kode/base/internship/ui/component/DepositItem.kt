package ru.kode.base.internship.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.kode.base.internship.products.domain.Currency
import ru.kode.base.internship.products.domain.Money
import ru.kode.base.internship.products.ui.R
import ru.kode.base.internship.ui.core.uikit.theme.AppTheme

@Composable
fun DepositItem(
  modifier: Modifier = Modifier,
  onDepositClick: () -> Unit,
  depositName: String,
  depositCurrency: Currency,
  depositRate: String,
  depositCloseDate: String,
  money: Money,
) {
  Row(
    modifier = modifier
      .heightIn(min = 72.dp)
      .fillMaxWidth()
      .clickable(onClick = onDepositClick),
    verticalAlignment = Alignment.CenterVertically,
  ) {
    Icon(
      modifier = Modifier.padding(16.dp),
      painter = painterResource(
        id = when (depositCurrency) {
          Currency.RUB -> R.drawable.ic_rub_40
          Currency.EUR -> R.drawable.ic_eu_40
          Currency.USD -> R.drawable.ic_dollar_40
        }
      ),
      contentDescription = "currency icon",
      tint = Color.Unspecified,
    )
    Column(
      horizontalAlignment = Alignment.Start,
      verticalArrangement = Arrangement.SpaceEvenly
    ) {
      Text(text = depositName, style = AppTheme.typography.body2)
      Text(
        text = money.format(),
        style = AppTheme.typography.body2,
        color = AppTheme.colors.contendAccentPrimary
      )
    }
    Spacer(modifier = Modifier.weight(1f))

    Column(
      modifier = Modifier.padding(end = 16.dp),
      horizontalAlignment = Alignment.End
    ) {
      Text(
        text = stringResource(id = R.string.rate_is, depositRate) + "%",
        style = AppTheme.typography.caption2,
        color = AppTheme.colors.contendTertiary
      )
      Text(
        text = stringResource(id = R.string.rate_until, depositCloseDate),
        style = AppTheme.typography.caption2,
        color = AppTheme.colors.contendTertiary
      )
    }
  }
}
