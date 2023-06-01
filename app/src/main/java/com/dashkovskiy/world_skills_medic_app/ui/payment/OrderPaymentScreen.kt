package com.dashkovskiy.world_skills_medic_app.ui.payment

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dashkovskiy.world_skills_medic_app.R

@Preview
@Composable
fun OrderPaymentScreenPreview() {
    OrderPaymentScreen()
}

private const val iconId = "icon"

@Composable
fun OrderPaymentScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 32.dp, horizontal = 20.dp)
    ) {
        val text = buildAnnotatedString {
            append("Не забудьте ознакомиться с ")
            appendInlineContent(iconId, "[icon]")
            withStyle(SpanStyle(color = Color(0xff1A6FEE))){
                append(" правилами подготовки к сдаче анализов")
            }
        }

        val inlineContent = remember {
            mapOf(
                Pair(
                    iconId,
                    InlineTextContent(
                        Placeholder(
                            width = 12.sp,
                            height = 12.sp,
                            placeholderVerticalAlign = PlaceholderVerticalAlign.AboveBaseline
                        )
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_file),
                            contentDescription = "",
                            tint = Color(0xff1A6FEE)
                        )
                    }
                )
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Оплата",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 17.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.W600
                )
            )
            Spacer(modifier = Modifier.padding(top = 89.dp))
            Image(
                painter = painterResource(R.drawable.onboard_1),
                contentDescription = null
            )
            Text(
                text = "Ваш заказ успешно оплачен!",
                color = Color(0xff00B712),
                style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 28.sp,
                    fontWeight = FontWeight.W600
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                text = "Вам осталось дождаться приезда медсестры и сдать анализы. До скорой встречи!",
                color = Color(0xff939396),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.W400
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                textAlign = TextAlign.Center,
                text = text,
                inlineContent = inlineContent,
                color = Color(0xff939396),
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.W400
                )
            )
        }
        Column(
            modifier = Modifier.align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(width = 1.dp, Color(0xff1A6FEE)),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xff1A6FEE)
                )
            ) {
                Text(text = "Чек покупки")
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                onClick = { },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff1A6FEE),
                    contentColor = Color.White,
                    disabledContainerColor = Color(0xffC9D4FB),
                    disabledContentColor = Color.White
                )
            ) {
                Text(text = "На главную")
            }
        }
    }
}