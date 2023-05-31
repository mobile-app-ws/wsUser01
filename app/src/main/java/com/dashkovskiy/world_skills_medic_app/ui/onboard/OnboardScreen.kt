package com.dashkovskiy.world_skills_medic_app.ui.onboard

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dashkovskiy.world_skills_medic_app.R
import org.koin.androidx.compose.getViewModel

data class OnboardItem(
    @DrawableRes val image: Int,
    @StringRes val title: Int,
    @StringRes val description: Int
)

val onboardItemsList = listOf(
    OnboardItem(
        title = R.string.onboard_1_title,
        image = R.drawable.onboard_1,
        description = R.string.onboard_1_description
    ),
    OnboardItem(
        title = R.string.onboard_2_title,
        image = R.drawable.onboard_2,
        description = R.string.onboard_2_description
    ),
    OnboardItem(
        title = R.string.onboard_3_title,
        image = R.drawable.onboard_3,
        description = R.string.onboard_3_description
    ),
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardScreen(
    viewModel: OnboardViewModel = getViewModel(),
    navigateNext : () -> Unit = {}
) {

    val state by viewModel.boardState.collectAsState()
    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(
                onClick = {
                    viewModel.saveOnboardFlag()
                    navigateNext()
                }
            ) {
                Text(
                    text = stringResource(
                        if (pagerState.currentPage == state.lastIndex) {
                            R.string.onboard_finish
                        } else {
                            R.string.onboard_skip
                        }
                    ),
                    //  Пропустить
                    style = TextStyle.Default.copy(
                        //fontFamily = FontFamily.
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xff57A9FF),
                        fontSize = 20.sp,
                        lineHeight = 24.sp
                    )
                )
            }
            Image(
                painter = painterResource(R.drawable.onboard_shape),
                contentDescription = null
            )
        }

        Spacer(modifier = Modifier.height(58.dp))

        Text(
            text = stringResource(id = state[pagerState.currentPage].title),
            style = TextStyle.Default.copy(
                //fontFamily = FontFamily.
                fontWeight = FontWeight.SemiBold,
                color = Color(0xff00B712),
                fontSize = 20.sp,
                lineHeight = 24.sp
            )
        )

        Spacer(modifier = Modifier.height(29.dp))

        Text(
            text = stringResource(id = state[pagerState.currentPage].description),
            style = TextStyle.Default.copy(
                //fontFamily = FontFamily.
                fontWeight = FontWeight.SemiBold,
                color = Color(0xff939396),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                lineHeight = 24.sp
            )
        )

        Spacer(modifier = Modifier.height(60.dp))

        StageIndicator(activeStage = pagerState.currentPage)

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .semantics {
                    contentDescription = "horizontal pager"
                },
            pageCount = state.size,
            verticalAlignment = Alignment.CenterVertically
        ) { page ->
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(state[page].image),
                contentDescription = null
            )
        }

    }
}

@Composable
fun StageIndicator(activeStage: Int) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(3) { index ->
            Box(
                modifier = Modifier.size(14.dp).clip(CircleShape).let {
                    if (activeStage == index) {
                        it.background(Color(0xFF57A9FF), shape = CircleShape)
                    } else {
                        it.border(width = 1.dp, color = Color(0xFF57A9FF), shape = CircleShape)
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun OnboardScreenPreview() {
    OnboardScreen()
}