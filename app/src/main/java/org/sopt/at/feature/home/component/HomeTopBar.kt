package org.sopt.at.feature.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.error
import org.sopt.at.R.drawable.ic_cast
import org.sopt.at.R.drawable.ic_tving_logo
import org.sopt.at.R.drawable.img_profile_placeholder
import org.sopt.at.core.designsystem.common.AtSoptDefaultTopBar
import org.sopt.at.core.designsystem.theme.ATSOPTANDROIDTheme
import org.sopt.at.core.util.noRippleClickable

@Composable
fun HomeTopBar(
    profileImageUrl: String,
    onProfileImageClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    AtSoptDefaultTopBar(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(ic_tving_logo),
                contentDescription = null,
                tint = Color.Unspecified,
            )
        },
        trailingIcon = {
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(ic_cast),
                    contentDescription = null,
                    tint = Color.Unspecified,
                )
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(profileImageUrl.takeIf { it.isNotBlank() })
                        .crossfade(true)
                        .error(img_profile_placeholder)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .noRippleClickable(onProfileImageClick),
                )
            }
        }
    )
}

@Preview
@Composable
private fun HomeTopBarPreview() {
    ATSOPTANDROIDTheme {
        HomeTopBar(
            profileImageUrl = "",
            onProfileImageClick = {},
        )
    }
}
