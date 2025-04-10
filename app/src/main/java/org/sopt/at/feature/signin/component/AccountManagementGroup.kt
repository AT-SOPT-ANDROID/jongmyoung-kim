package org.sopt.at.feature.signin.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.at.R.string.sign_in_find_id
import org.sopt.at.R.string.sign_in_find_password
import org.sopt.at.R.string.sign_up
import org.sopt.at.R.string.vertical_bar
import org.sopt.at.core.designsystem.theme.ATSOPTANDROIDTheme
import org.sopt.at.core.util.noRippleClickable

@Composable
fun AccountManagementGroup(
    navigateToSignUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .padding(horizontal = 52.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(sign_in_find_id),
            style = MaterialTheme.typography.labelLarge.copy(
                color = Color.LightGray,
            )
        )
        Text(
            text = stringResource(vertical_bar),
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.LightGray,
            )
        )
        Text(
            text = stringResource(sign_in_find_password),
            style = MaterialTheme.typography.labelLarge.copy(
                color = Color.LightGray,
            )
        )
        Text(
            text = stringResource(vertical_bar),
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.LightGray,
            )
        )
        Text(
            text = stringResource(sign_up),
            style = MaterialTheme.typography.labelLarge.copy(
                color = Color.LightGray,
            ),
            modifier = Modifier.noRippleClickable(navigateToSignUp),
        )
    }
}

@Preview
@Composable
private fun AccountManagementGroupPreview() {
    ATSOPTANDROIDTheme {
        AccountManagementGroup(
            navigateToSignUp = { },
        )
    }
}
