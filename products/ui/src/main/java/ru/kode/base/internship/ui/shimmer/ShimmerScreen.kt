package ru.kode.base.internship.ui.shimmer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ru.kode.base.internship.ui.core.uikit.theme.AppTheme

@Composable
fun ShimmerScreen(modifier: Modifier) {
  Column(modifier = modifier.background(color = AppTheme.colors.backgroundSecondary)) {
    Row(Modifier.fillMaxWidth()) {
      Row(
        modifier = Modifier
          .padding(start = 16.dp, end = 16.dp, top = 16.dp)
          .size(width = 72.dp, height = 14.dp)
          .clip(shape = RoundedCornerShape(16.dp))
      )
      {
        Box(
          modifier = Modifier
            .fillMaxSize()
            .shimmerLoadingAnimation()
        )
      }
    }
    ShimmerItem()
    Divider(
      modifier = Modifier.padding(start = 72.dp, end = 16.dp),
      color = AppTheme.colors.contendSecondary,
      thickness = 2.dp
    )
    ShimmerItem()
  }
}


@Composable
fun ShimmerItem() {
  Row(
    modifier = Modifier
      .background(color = AppTheme.colors.backgroundSecondary)
      .heightIn(min = 72.dp)
      .fillMaxWidth()
      .padding(16.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Row(
      modifier = Modifier
        .size(40.dp)
        .aspectRatio(1f)
        .clip(CircleShape)
    )
    {
      Box(
        modifier = Modifier
          .shimmerLoadingAnimation()
          .fillMaxSize()
      )
    }
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
      horizontalAlignment = Alignment.Start,
      verticalArrangement = Arrangement.spacedBy(16.dp)
    )
    {
      Row(
        modifier = Modifier
          .size(width = 232.dp, height = 16.dp)
          .clip(shape = RoundedCornerShape(16.dp))
      )
      {
        Box(
          modifier = Modifier
            .shimmerLoadingAnimation()
            .fillMaxSize()
        )
      }
      Row(
        modifier = Modifier
          .size(width = 132.dp, height = 12.dp)
          .clip(shape = RoundedCornerShape(16.dp))
      )
      {
        Box(
          modifier = Modifier
            .shimmerLoadingAnimation()
            .fillMaxSize()
        )
      }
    }
  }
}

