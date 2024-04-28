package ru.kode.base.internship.ui.carddetails.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.kode.base.internship.products.ui.R

@Composable
fun Bar(
  modifier: Modifier = Modifier,
  onClick: () -> Unit,
) {
  Box(
    modifier = modifier,
  ) {
    IconButton(
      modifier = Modifier.padding(start = 16.dp),
      onClick = onClick
    ) {
      Icon(
        modifier = Modifier.size(24.dp),
        painter = painterResource(id = R.drawable.ic_left_24x24),
        contentDescription = "back"
      )
    }
    Text(
      modifier = Modifier
        .align(Alignment.Center)
        .fillMaxWidth(),
      text = "Карты",
      textAlign = TextAlign.Center
    )
  }
}
