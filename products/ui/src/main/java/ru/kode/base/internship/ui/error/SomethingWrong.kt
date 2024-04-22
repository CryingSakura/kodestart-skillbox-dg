package ru.kode.base.internship.ui.error

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.kode.base.internship.products.ui.R
import ru.kode.base.internship.ui.core.uikit.theme.AppTheme

@Composable
fun SomethingWrongComponent(
  onClick: () -> Unit,
) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .height(173.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  )
  {
    Text(
      text = stringResource(id = R.string.something_went_wrong),
      textAlign = TextAlign.Center,
      color = AppTheme.colors.textPrimary
    )
    Text(
      text = stringResource(id = R.string.failed_to_load),
      textAlign = TextAlign.Center,
      color = AppTheme.colors.textPrimary
    )
    TextButton(
      onClick = onClick,
      colors = ButtonDefaults.buttonColors(
        backgroundColor = AppTheme.colors.backgroundPrimary,
        contentColor = AppTheme.colors.contendAccentPrimary,
      )
    ) {
      Text(
        text = stringResource(id = R.string.reload),
        textAlign = TextAlign.Center,
      )
    }
  }
}

@Composable
@Preview(showBackground = true)
private fun SomethingWrongPreview() {
  AppTheme {
    SomethingWrongComponent(onClick = {})
  }
}