package org.sopt.at.feature.signin.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.at.R.string.sign_in_policy
import org.sopt.at.core.designsystem.theme.ATSOPTANDROIDTheme
import org.sopt.at.core.designsystem.theme.AtSoptTheme

@Composable
fun SignInFooter(
    modifier: Modifier = Modifier,
) {
    val policyText = stringResource(sign_in_policy)
    val annotatedString = buildAnnotatedString {
        append(policyText)
        addStyle(
            style = SpanStyle(textDecoration = TextDecoration.Underline),
            start = 31,
            end = 42
        )
        addStyle(
            style = SpanStyle(textDecoration = TextDecoration.Underline),
            start = 49,
            end = 55
        )
    }

    Text(
        text = annotatedString,
        style = AtSoptTheme.typography.label10m.copy(
            color = AtSoptTheme.colors.gray300,
        ),
        textAlign = TextAlign.Center,
        modifier = modifier
            .fillMaxWidth(),
    )
}

@Preview
@Composable
private fun SignInFooterPreview() {
    ATSOPTANDROIDTheme {
        SignInFooter()
    }
}
