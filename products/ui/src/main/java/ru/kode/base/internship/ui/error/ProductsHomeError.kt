package ru.kode.base.internship.ui.error

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.kode.base.internship.products.ui.R
import ru.kode.base.internship.ui.core.uikit.component.PrimaryButton
import ru.kode.base.internship.ui.core.uikit.theme.AppTheme

@Composable
fun ProductsHomeError(
  onRefreshClick: () -> Unit,
  onCloseClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    IconButton(
      modifier = Modifier
        .align(Alignment.Start)
        .padding(16.dp),
      onClick = onCloseClick
    ) {
      Icon(painter = painterResource(id = R.drawable.ic_cross_24x24), contentDescription = "close error")
    }
    Spacer(modifier = Modifier.weight(1f))
    Icon(
      painter = painterResource(id = R.drawable.ic_server_unavailable_148x148),
      contentDescription = null,
      tint = Color.Unspecified
    )
    Spacer(modifier = Modifier.height(32.dp))
    Text(
      text = stringResource(id = R.string.attention),
      color = AppTheme.colors.textPrimary,
      style = AppTheme.typography.subtitle,
      textAlign = TextAlign.Center,
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
      modifier = Modifier.padding(),
      text = stringResource(id = R.string.server_temporarily_unavailable),
      color = AppTheme.colors.textPrimary,
      style = AppTheme.typography.body2,
      textAlign = TextAlign.Center,
    )
    Spacer(modifier = Modifier.weight(1f))
    PrimaryButton(
      modifier = Modifier
        .fillMaxWidth()
        .heightIn(min = 52.dp)
        .padding(bottom = 16.dp),
      onClick = onRefreshClick,
      text = stringResource(id = R.string.repeat)
    )
  }
}

@Composable
@Preview
private fun ErrorPreview() {
  AppTheme {
    ProductsHomeError(onRefreshClick = { /*TODO*/ }, onCloseClick = { /*TODO*/ })
  }
}
