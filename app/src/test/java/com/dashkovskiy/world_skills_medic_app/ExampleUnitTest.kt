package com.dashkovskiy.world_skills_medic_app

import com.dashkovskiy.world_skills_medic_app.ui.onboard.OnboardViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class OnBoardViewModelTest {

    @Mock
    lateinit var vm : OnboardViewModel

    @Test
    fun testSaveOnBoardFlag() {
        vm.saveOnboardFlag()
        Mockito.verify(vm,Mockito.times(1)).saveOnboardFlag()
    }
}