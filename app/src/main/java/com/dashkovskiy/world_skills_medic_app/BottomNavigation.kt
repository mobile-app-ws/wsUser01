package com.dashkovskiy.world_skills_medic_app

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dashkovskiy.world_skills_medic_app.ui.Nav

data class BottomNavItem(
    @DrawableRes val icon: Int,
    val title: String,
    val route: String
)

val inactiveItemColor = Color(0xffB8C1CC)
val activeItemColor = Color(0xff1A6FEE)

@Composable
fun BottomNavItem(
    modifier : Modifier = Modifier,
    painter: Painter,
    title: String,
    isActive: Boolean = false,
    onNavItemClick: () -> Unit = {}
) {
    Column(
        modifier = modifier.clickable {
            onNavItemClick()
        },
        verticalArrangement = Arrangement.spacedBy(6.dp,Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painter,
            contentDescription = null,
            tint = if (isActive) activeItemColor else inactiveItemColor,
        )
        Text(
            text = title,
            color = if (isActive) activeItemColor else inactiveItemColor,
            style = TextStyle(fontSize = 12.sp, lineHeight = 16.sp, fontWeight = FontWeight.W400)
        )
    }
}

val bottomNavList = listOf(
    BottomNavItem(
        icon = R.drawable.ic_analyzes,
        title = "Анализы",
        route = Nav.Analyzes.route
    ),
    BottomNavItem(
        icon = R.drawable.ic_results,
        title = "Результаты",
        route = Nav.Results.route
    ),
    BottomNavItem(
        icon = R.drawable.ic_support,
        title = "Поддержка",
        route = Nav.Support.route
    ),
    BottomNavItem(
        icon = R.drawable.ic_profile,
        title = "Профиль",
        route = Nav.Profile.route
    ),
)

@Composable
fun BottomNavigation(navHostController: NavHostController) {

    val backStack by navHostController.currentBackStackEntryAsState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(88.dp)
            .padding(horizontal = 20.dp)
            .padding(top = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        bottomNavList.forEach { bottomItem ->
            BottomNavItem(
                modifier = Modifier.weight(1f),
                painter = painterResource(bottomItem.icon),
                title = bottomItem.title,
                isActive = backStack?.destination?.route == bottomItem.route,
                onNavItemClick = {
                    navHostController.navigate(bottomItem.route) {
                        popUpTo(bottomItem.route) { inclusive = true }
                    }
                }
            )
        }
    }

}

@Preview
@Composable
fun BottomNavigationPreview() {
    BottomNavigation(navHostController = rememberNavController())
}