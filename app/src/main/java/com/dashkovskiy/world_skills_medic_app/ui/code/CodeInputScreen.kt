package com.dashkovskiy.world_skills_medic_app.ui.code

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dashkovskiy.world_skills_medic_app.R
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun CodeInputScreen(
    email : String,
    viewModel: CodeInputViewModel = getViewModel{ parametersOf(email) },
    navigateBack: () -> Unit = {},
    navigateToCreatePasswordScreen: () -> Unit = {}
) {
    val state by viewModel.viewState.collectAsState()

    if (state.isSignInSuccessful) {
        navigateToCreatePasswordScreen()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(start = 20.dp)
                .align(Alignment.TopStart)
                .size(32.dp)
                .background(
                    color = Color(0xffF5F5F9),
                    shape = RoundedCornerShape(8.dp)
                )
                .clip(RoundedCornerShape(8.dp))
                .clickable { navigateBack() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_arrow_back),
                contentDescription = null,
                tint = Color(0xff7E7E9A)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 132.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Введит код из email",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 17.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.W700
                )
            )
            Spacer(modifier = Modifier.height(24.dp))
            CodeInputField(
                code = state.code,
                onValueChanged = viewModel::setCode
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier.padding(horizontal = 66.dp),
                text = "Отправить код повторно можно будет через ${state.time} секунд",
                textAlign = TextAlign.Center,
                color = Color(0xFF939396),
                style = TextStyle(
                    fontSize = 15.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.W400
                )
            )
        }
    }


}

@Composable
fun CodeInputField(
    code: String,
    onValueChanged: (String) -> Unit
) {
    BasicTextField(
        value = code,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = {
            if (it.length <= 4) {
                onValueChanged(it)
            }
        },
        decorationBox = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                repeat(4) { index ->
                    NumberBox(
                        number = code.getOrElse(index) { ' ' }
                    )
                }
            }
        }
    )
}

@Composable
fun NumberBox(number: Char) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .background(
                color = Color(0xffF5F5F9),
                shape = RoundedCornerShape(10.dp)
            )
            .border(
                width = 1.dp,
                color = Color(0xFFEBEBEB),
                shape = RoundedCornerShape(10.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(text = number.toString(), color = Color.Black)
    }
}

@Preview
@Composable
fun CodeInputScreenPreview() {
    CodeInputScreen(email = "")
}