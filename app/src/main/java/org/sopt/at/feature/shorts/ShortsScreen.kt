package org.sopt.at.feature.shorts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.at.R.string.ic_shorts_desc
import org.sopt.at.core.designsystem.theme.ATSOPTANDROIDTheme
import org.sopt.at.core.designsystem.theme.AtSoptTheme

@Composable
fun ShortsRoute(
    modifier: Modifier = Modifier,
) {
    ShortsScreen(
        modifier,
    )
}

@Composable
fun ShortsScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = AtSoptTheme.colors.black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(ic_shorts_desc),
            style = AtSoptTheme.typography.display56b.copy(
                color = AtSoptTheme.colors.white,
            ),
        )
    }
}

@Preview
@Composable
private fun ShortsScreenPreview() {
    ATSOPTANDROIDTheme {
        ShortsScreen()
    }
}
