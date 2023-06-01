package com.dashkovskiy.world_skills_medic_app.ui.password

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dashkovskiy.world_skills_medic_app.R
import org.koin.androidx.compose.getViewModel

@Preview
@Composable
fun CreatePasswordScreenPreview() {
    CreatePasswordScreen()
}

@Composable
fun CreatePasswordScreen(
    viewModel: CreatePasswordViewModel = getViewModel(),
    navigateNext: () -> Unit = {}
) {
    val state by viewModel.viewState.collectAsState()

    if (state.isPasswordSaved) {
        navigateNext.also {
            viewModel.setPasswordSaved(isSaved = false)
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(
                onClick = navigateNext
            ) {
                Text(
                    text = stringResource(R.string.onboard_skip),
                    color = Color(0xff1A6FEE)
                )
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Создайте пароль",
            color = Color.Black,
            style = TextStyle(
                fontSize = 24.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight.W700
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Для защиты ваших персональных данных",
            color = Color(0xff939396),
            style = TextStyle(
                fontSize = 15.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.W400
            )
        )
        Spacer(modifier = Modifier.height(56.dp))
        PasswordLengthIndicator(pwdLength = state.pwd.length)
        Spacer(modifier = Modifier.height(60.dp))
        LazyVerticalGrid(
            modifier = Modifier.padding(horizontal = 43.dp),
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(9) { number ->
                NumberBtn(
                    number = number + 1,
                    onBtnClick = viewModel::setPassword
                )
            }
            item {

            }
            item {
                NumberBtn(
                    number = 0,
                    onBtnClick = viewModel::setPassword
                )
            }
            item {
                Box(
                    modifier = Modifier
                        .requiredSize(80.dp)
                        .clip(CircleShape)
                        .clickable { viewModel.deleteNumber() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_delete_text),
                        tint = Color.Black,
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Composable
fun NumberBtn(
    number: Int,
    onBtnClick: (Int) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .requiredSize(80.dp)
            .background(color = Color(0xFFF5F5F9), shape = CircleShape)
            .clip(CircleShape)
            .clickable { onBtnClick(number) },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = number.toString(),
            color = Color.Black,
            style = TextStyle(
                fontSize = 24.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight.W600
            )
        )
    }
}

@Composable
fun PasswordLengthIndicator(pwdLength: Int = 0) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(pwdLength) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF57A9FF), shape = CircleShape)
            )
        }
        repeat(4 - pwdLength) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .clip(CircleShape)
                    .border(width = 1.dp, color = Color(0xFF57A9FF), shape = CircleShape)
            )
        }
    }
}
