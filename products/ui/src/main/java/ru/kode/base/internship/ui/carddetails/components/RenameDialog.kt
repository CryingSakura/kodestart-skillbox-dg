package ru.kode.base.internship.ui.carddetails.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import ru.kode.base.internship.products.ui.R
import ru.kode.base.internship.ui.core.uikit.theme.AppTheme

@Composable
fun RenameDialog(
  onDismissRequest: () -> Unit,
  value: String,
  onValueChanged: (String) -> Unit,
  onConfirmRequest: () -> Unit,
) {
  Dialog(
    onDismissRequest = onDismissRequest,
  ) {
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .height(248.dp)
        .padding(16.dp),
      shape = RoundedCornerShape(16.dp),
      backgroundColor = AppTheme.colors.backgroundPrimary,
      contentColor = AppTheme.colors.contendAccentTertiary
    ) {
      Column {
        Column(Modifier.padding(16.dp)) {
          Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.rename),
            textAlign = TextAlign.Center
          )
          Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.enter_new_name),
            textAlign = TextAlign.Center
          )
          OutlinedTextField(
            modifier = Modifier
              .fillMaxWidth()
              .padding(start = 16.dp, top = 16.dp, end = 16.dp),
            value = value,
            onValueChange = onValueChanged,
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
              backgroundColor = AppTheme.colors.backgroundPrimary,
              textColor = AppTheme.colors.contendAccentTertiary,
              unfocusedIndicatorColor = AppTheme.colors.indicatorContendSuccess,
              focusedIndicatorColor = AppTheme.colors.indicatorContendSuccess,
              cursorColor = AppTheme.colors.indicatorContendSuccess,
            ),
          )
        }
        Row(
          Modifier
            .fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceEvenly,
          verticalAlignment = Alignment.CenterVertically
        ) {
          TextButton(
            colors = ButtonDefaults.buttonColors(
              backgroundColor = AppTheme.colors.backgroundPrimary,
              contentColor = AppTheme.colors.contendAccentTertiary
            ),
            onClick = onDismissRequest
          ) {
            Text(
              text = stringResource(id = R.string.cancel),
              textAlign = TextAlign.Center
            )
          }
          TextButton(
            colors = ButtonDefaults.buttonColors(
              backgroundColor = AppTheme.colors.backgroundPrimary,
              contentColor = AppTheme.colors.contendAccentTertiary
            ),
            onClick = { onConfirmRequest() }
          ) {
            Text(
              text = stringResource(id = R.string.confirm),
              textAlign = TextAlign.Center
            )
          }
        }
      }
    }
  }
}
