package com.dashkovskiy.world_skills_medic_app.ui.patientmap

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dashkovskiy.world_skills_medic_app.R
import com.dashkovskiy.world_skills_medic_app.api.Gender
import com.dashkovskiy.world_skills_medic_app.ui.email.MedicAppTextField
import org.koin.androidx.compose.getViewModel

enum class DropDownState {
    ACTIVE, INACTIVE
}

@Composable
fun MedicTextFieldGenderPicker(
    modifier: Modifier = Modifier,
    gender: Gender = Gender.NOT_SPECIFIED,
    onValueChanged: (Gender) -> Unit
) {
    var dropDownState by remember {
        mutableStateOf(DropDownState.INACTIVE)
    }
    val rotate by animateFloatAsState(
        targetValue = if (dropDownState == DropDownState.ACTIVE) 180f else 0f
    )

    BasicTextField(
        modifier = modifier,
        value = when (gender) {
            Gender.FEMALE -> "Мужской"
            Gender.MALE -> "Женский"
            else -> "Пол"
        },
        singleLine = true,
        onValueChange = { },
        readOnly = true,
        decorationBox = { innerTextField ->
            Row(
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
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                innerTextField()
                IconButton(
                    onClick = {
                        dropDownState = if (dropDownState == DropDownState.ACTIVE) {
                            DropDownState.INACTIVE
                        } else {
                            DropDownState.ACTIVE
                        }
                    }
                ) {
                    Icon(
                        modifier = Modifier.rotate(rotate),
                        painter = painterResource(R.drawable.ic_arrow_down),
                        tint = Color(0xFF7E7E9A),
                        contentDescription = null
                    )
                }
            }
        }
    )

    DropdownMenu(
        expanded = dropDownState == DropDownState.ACTIVE,
        onDismissRequest = { dropDownState = DropDownState.INACTIVE }
    ) {
        DropdownMenuItem(
            text = { Text("Мужской") },
            onClick = {
                onValueChanged(Gender.FEMALE)
                dropDownState = DropDownState.INACTIVE
            }
        )
        DropdownMenuItem(
            text = { Text(text = "Женский") },
            onClick = {
                onValueChanged(Gender.MALE)
                dropDownState = DropDownState.INACTIVE
            }
        )
    }
}

@Composable
fun PatientMapScreen(viewModel: PatientMapViewModel = getViewModel()) {

    val state by viewModel.viewState.collectAsState()

    Column(
        modifier = Modifier
            .padding(top = 40.dp)
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Start,
                text = "Создание карты пациента",
                style = TextStyle(
                    fontSize = 24.sp,
                    lineHeight = 28.sp,
                    fontWeight = FontWeight.W700
                )
            )
            TextButton(
                onClick = { /*TODO*/ }
            ) {
                Text(
                    text = stringResource(R.string.onboard_skip),
                    color = Color(0xff1A6FEE)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Без карты пациента вы не сможете заказать анализы.",
            color = Color(0xff939396),
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.W400
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "В картах пациентов будут храниться результаты анализов вас и ваших близких.",
            color = Color(0xff939396),
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.W400
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        MedicAppTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.name,
            placeholder = "Имя",
            onValuesChanged = viewModel::setName
        )

        Spacer(modifier = Modifier.height(24.dp))

        MedicAppTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.patronymic,
            placeholder = "Отчество",
            onValuesChanged = viewModel::setPatronymic
        )

        Spacer(modifier = Modifier.height(24.dp))

        MedicAppTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.surname,
            placeholder = "Фамилия",
            onValuesChanged = viewModel::setSurname
        )

        Spacer(modifier = Modifier.height(24.dp))

        MedicAppTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.birthday,
            placeholder = "Дата рождения",
            onValuesChanged = viewModel::setBirthday
        )

        Spacer(modifier = Modifier.height(24.dp))

        MedicTextFieldGenderPicker(
            modifier = Modifier.fillMaxWidth(),
            gender = state.gender,
            onValueChanged = viewModel::setGender
        )

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            onClick = { },
            enabled = state.btnEnabled,
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xff1A6FEE),
                contentColor = Color.White,
                disabledContainerColor = Color(0xffC9D4FB),
                disabledContentColor = Color.White
            )
        ) {
            Text(text = "Создать")
        }
    }
}

@Preview
@Composable
fun PatientMapScreenPreview() {
    PatientMapScreen()
}