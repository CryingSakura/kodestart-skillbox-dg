package ru.kode.base.internship.ui.carddetails.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.kode.base.internship.ui.core.uikit.theme.AppTheme

@Composable
fun CardDetailsListItem(
  modifier: Modifier = Modifier,
  icon: Int,
  text: String,
  onClick: () -> Unit,
) {
  Row(
    modifier = modifier.clickable { onClick() },
    verticalAlignment = Alignment.CenterVertically
  ) {
    Icon(
      modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
      painter = painterResource(id = icon),
      contentDescription = ""
    )
    Text(
      modifier = Modifier.padding(start = 16.dp),
      text = text,
      color = AppTheme.colors.textSecondary,
      style = AppTheme.typography.body2,
    )
  }
}
