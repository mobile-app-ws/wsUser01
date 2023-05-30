package com.dashkovskiy.world_skills_medic_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import com.dashkovskiy.world_skills_medic_app.ui.theme.WorldskillsmedicappTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorldskillsmedicappTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

fun testFun(){

}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WorldskillsmedicappTheme {
        Greeting("Android")
    }
}

data class TestState(val name : String = "")

class TestViewModel : ViewModel(){

    private val _viewState = MutableStateFlow(TestState())
    val viewState = _viewState.asStateFlow()

    private val state : TestState
        get() = _viewState.value
}