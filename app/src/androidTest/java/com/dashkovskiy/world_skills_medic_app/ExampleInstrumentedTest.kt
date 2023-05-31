package com.dashkovskiy.world_skills_medic_app

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeLeft
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dashkovskiy.world_skills_medic_app.ui.LocalStorage
import com.dashkovskiy.world_skills_medic_app.ui.onboard.OnboardItem
import com.dashkovskiy.world_skills_medic_app.ui.onboard.OnboardScreen
import com.dashkovskiy.world_skills_medic_app.ui.onboard.OnboardViewModel

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule


@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun testCorrectImageAndTextOrder() {
        val vm = OnboardViewModel(LocalStorage(InstrumentationRegistry.getInstrumentation().targetContext))

        rule.setContent {
            OnboardScreen()
        }

        rule.onNodeWithContentDescription("onboard pager").performTouchInput { swipeLeft() }

        assertEquals(
            vm.boardState.value.first(),
            OnboardItem(
                title = R.string.onboard_1_title,
                image = R.drawable.onboard_1,
                description = R.string.onboard_1_description
            )
        )

        rule.onNodeWithContentDescription("onboard pager").performTouchInput { swipeLeft() }

        assertEquals(
            vm.boardState.value.first(),
            OnboardItem(
                title = R.string.onboard_2_title,
                image = R.drawable.onboard_2,
                description = R.string.onboard_2_description
            )
        )

        rule.onNodeWithContentDescription("onboard pager").performTouchInput { swipeLeft() }

        assertEquals(
            vm.boardState.value.first(),
            OnboardItem(
                title = R.string.onboard_3_title,
                image = R.drawable.onboard_3,
                description = R.string.onboard_3_description
            )
        )
    }


    @Test
    fun testCorrectItemsCount(){
        val vm = OnboardViewModel(LocalStorage(InstrumentationRegistry.getInstrumentation().targetContext))

        rule.setContent {
            OnboardScreen()
        }

        assertEquals(
            vm.boardState.value.count(),
            3
        )
        rule.onNodeWithContentDescription("onboard pager").performTouchInput { swipeLeft() }


        assertEquals(
            vm.boardState.value.count(),
            2
        )

        rule.onNodeWithContentDescription("onboard pager").performTouchInput { swipeLeft() }


        assertEquals(
            vm.boardState.value.count(),
            1
        )
    }

    @Test
    fun testCorrectButtonText(){
        rule.setContent {
            OnboardScreen()
        }
        rule.onNodeWithContentDescription("onboard pager").performTouchInput { swipeLeft() }
        rule.onNodeWithText("Пропустить").assertDoesNotExist()
        rule.onNodeWithContentDescription("onboard pager").performTouchInput { swipeLeft() }
        rule.onNodeWithText("Пропустить").assertDoesNotExist()
        rule.onNodeWithContentDescription("onboard pager").performTouchInput { swipeLeft() }
        rule.onNodeWithText("Завершить").assertDoesNotExist()
    }


    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.dashkovskiy.world_skills_medic_app", appContext.packageName)
    }
}