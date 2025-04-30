package org.sopt.at.feature.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.at.R.string.ic_history_desc
import org.sopt.at.core.designsystem.theme.ATSOPTANDROIDTheme
import org.sopt.at.core.designsystem.theme.AtSoptTheme
import org.sopt.at.feature.history.component.HistoryFab

@Composable
fun HistoryRoute(
    modifier: Modifier = Modifier,
) {
    HistoryScreen(
        modifier,
    )
}

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = AtSoptTheme.colors.black),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = stringResource(ic_history_desc),
                style = MaterialTheme.typography.displayLarge.copy(
                    color = AtSoptTheme.colors.white,
                ),
            )
        }
        HistoryFab(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp),
        )
    }
}

@Preview
@Composable
private fun HistoryScreenPreview() {
    ATSOPTANDROIDTheme {
        HistoryScreen()
    }
}
