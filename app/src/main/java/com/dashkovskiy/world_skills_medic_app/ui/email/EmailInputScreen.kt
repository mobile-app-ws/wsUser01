package com.dashkovskiy.world_skills_medic_app.ui.email

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dashkovskiy.world_skills_medic_app.R
import org.koin.androidx.compose.getViewModel

@Composable
fun EmailInputScreen(
    viewModel: EmailInputViewModel = getViewModel(),
    navigateToSendCodeScreen : (String) -> Unit = {}
) {
    val state by viewModel.viewState.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 77.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(R.drawable.hello),
                    contentDescription = null
                )
                Text(
                    text = "Добро пожаловать!",
                    color = Color.Black,
                    style = TextStyle(
                        fontWeight = FontWeight.W700,
                        fontSize = 24.sp,
                        lineHeight = 28.sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Войдите, чтобы пользоваться функциями приложения",
                textAlign = TextAlign.Start,
                color = Color.Black,
                style = TextStyle(
                    fontWeight = FontWeight.W400,
                    fontSize = 15.sp,
                    lineHeight = 20.sp
                )
            )

            Spacer(modifier = Modifier.height(64.dp))

            Text(
                text = "Вход по E-mail",
                color = Color(0xff7E7E9A)
            )

            Spacer(modifier = Modifier.height(4.dp))

            MedicAppTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.email,
                placeholder = "example@mail.ru",
                onValuesChanged = viewModel::setEmail
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                onClick = { navigateToSendCodeScreen(state.email) },
                enabled = state.isEmailValid,
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff1A6FEE),
                    contentColor = Color.White,
                    disabledContainerColor = Color(0xffC9D4FB),
                    disabledContentColor = Color.White
                )
            ) {
                Text(text = "Далее")
            }
        }
    }
}

@Composable
fun MedicAppTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String,
    onValuesChanged: (String) -> Unit
) {
    BasicTextField(
        modifier = modifier,
        value = value,
        singleLine = true,
        onValueChange = onValuesChanged,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .height(48.dp)
                    .background(
                        color = Color(0xffF5F5F9),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = Color(0xFFEBEBEB),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(horizontal = 14.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        color = Color.Black.copy(alpha = 0.5f)
                    )
                }
                innerTextField()
            }
        }
    )
}

@Preview
@Composable
fun SignInScreenPreview() {
    EmailInputScreen()
}