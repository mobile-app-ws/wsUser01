package com.dashkovskiy.world_skills_medic_app.ui.onboard

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.dashkovskiy.world_skills_medic_app.R

data class OnboardItem(
    @DrawableRes val image : Int,
    @StringRes val description : Int
)

val onboardItemsList = listOf(
    OnboardItem(
        image = R.drawable.onboard_1,
        description = R.string.onboard_1_description
    ),
    OnboardItem(
        image = R.drawable.onboard_2,
        description = R.string.onboard_2_description
    ),
    OnboardItem(
        image = R.drawable.onboard_3,
        description = R.string.onboard_3_description
    ),
)

@Composable
fun OnboardScreen(){

}