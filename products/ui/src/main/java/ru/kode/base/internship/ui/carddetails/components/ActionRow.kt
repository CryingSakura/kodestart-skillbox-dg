package ru.kode.base.internship.ui.carddetails.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.kode.base.internship.products.ui.R
import ru.kode.base.internship.ui.carddetails.states.CardListState
import ru.kode.base.internship.ui.core.uikit.theme.AppTheme

@Composable
fun ActionRow(
  modifier: Modifier = Modifier,
  onActionsClick: () -> Unit,
  onHistoryClick: () -> Unit,
  onPaymentsClick: () -> Unit,
  state: CardListState,
) {
  Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceEvenly
  ) {
    Button(
      modifier = Modifier.size(56.dp),
      onClick = onHistoryClick,
      shape = CircleShape,
      colors = ButtonDefaults.buttonColors(
        backgroundColor = if (state == CardListState.History)
          {
            AppTheme.colors.contendAccentTertiary
          } else {
          AppTheme.colors.contendSecondary
        },
        contentColor = if (state == CardListState.History)
          {
            AppTheme.colors.contendAccentPrimary
          } else {
          AppTheme.colors.contendAccentTertiary
        }
      )
    ) {
      Icon(
        painter = painterResource(id = R.drawable.ic_history_24x24),
        contentDescription = null,
      )
    }
    Button(
      modifier = Modifier.size(56.dp),
      onClick = onActionsClick,
      shape = CircleShape,
      colors = ButtonDefaults.buttonColors(
        backgroundColor = if (state == CardListState.CardActions)
          {
            AppTheme.colors.contendAccentTertiary
          } else {
          AppTheme.colors.contendSecondary
        },
        contentColor = if (state == CardListState.CardActions)
          {
            AppTheme.colors.contendAccentPrimary
          } else {
          AppTheme.colors.contendAccentTertiary
        }
      )
    ) {
      Icon(
        painter = painterResource(id = R.drawable.ic_card_info_24x24),
        contentDescription = null,
      )
    }
    Button(
      modifier = Modifier.size(56.dp),
      onClick = onPaymentsClick,
      shape = CircleShape,
      colors = ButtonDefaults.buttonColors(
        backgroundColor = if (state == CardListState.Payments)
          {
            AppTheme.colors.contendAccentTertiary
          } else {
          AppTheme.colors.contendSecondary
        },
        contentColor = if (state == CardListState.Payments)
          {
            AppTheme.colors.contendAccentPrimary
          } else {
          AppTheme.colors.contendAccentTertiary
        }
      )
    ) {
      Icon(
        painter = painterResource(id = R.drawable.ic_payments_24x24),
        contentDescription = null,
      )
    }
  }
}
