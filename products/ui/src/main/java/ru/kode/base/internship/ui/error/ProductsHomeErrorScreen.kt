package ru.kode.base.internship.ui.error

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.kode.base.internship.products.ui.R
import ru.kode.base.internship.ui.core.uikit.theme.AppTheme

@Composable
fun ProductsHomeErrorScreen(
  onRefreshClick: () -> Unit,
  onCloseClick: () -> Unit,
  refreshing: Boolean,
) {

  Box(modifier = Modifier.fillMaxSize()) {
    IconButton(
      modifier = Modifier
        .padding(16.dp),
      onClick = { onCloseClick }
    ) {
      Icon(painter = painterResource(id = R.drawable.ic_cross_24x24), contentDescription = "close error")
    }
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Spacer(modifier = Modifier.height(134.dp))
      Icon(
        painter = painterResource(id = R.drawable.ic_server_unavailable_148x148),
        contentDescription = "error ic",
        tint = Color.Unspecified
      )
      Spacer(modifier = Modifier.height(32.dp))
      Text(
        text = stringResource(id = R.string.attention),
        color = AppTheme.colors.textPrimary,
        fontSize = 17.sp,
        textAlign = TextAlign.Center
      )
      Spacer(modifier = Modifier.height(16.dp))
      Text(
        modifier = Modifier.padding(),
        text = stringResource(id = R.string.server_temporarily_unavailable),
        color = AppTheme.colors.textPrimary,
        fontSize = 15.sp,
        textAlign = TextAlign.Center
      )
      Spacer(modifier = Modifier.weight(1f))
      Button(
        modifier = Modifier
          .fillMaxWidth()
          .heightIn(min = 52.dp),
        onClick = onRefreshClick,
        colors = ButtonDefaults.buttonColors(
          backgroundColor = AppTheme.colors.primaryButton,
          contentColor = Color.White,
        ),
        shape = RoundedCornerShape(32.dp),
      ) {
        if (refreshing) {
          CircularProgressIndicator(
            modifier = Modifier
              .size(40.dp),
            color = AppTheme.colors.contendAccentPrimary,
          )
        } else {
          Text(text = stringResource(id = R.string.repeat))
        }
      }
    }
  }

}