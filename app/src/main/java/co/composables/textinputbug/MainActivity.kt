package co.composables.textinputbug

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import co.composables.textinputbug.ui.theme.TextInputBugTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TextInputBugTheme {
                Column {
                    val viewModel = viewModel { MainViewModel() }

                    StatelessTextField(
                        text = viewModel.text,
                        onTextChanged = {
                            viewModel.onTextChanged(it)
                        }
                    )
                    StatefullTextField(
                        text = viewModel.text,
                        onTextChanged = {
                            viewModel.onTextChanged(it)
                        }
                    )
                }
            }
        }
    }

    @Composable
    private fun StatefullTextField(
        text: String,
        onTextChanged: (String) -> Unit,
    ) {
        var state by remember { mutableStateOf(text) }
        TextField(
            value = state,
            onValueChange = {
                state = it
                onTextChanged(it)
            },
            label = {
                Text("Statefull")
            }
        )

    }

    @Composable
    private fun StatelessTextField(
        text: String,
        onTextChanged: (String) -> Unit,
    ) {
        TextField(
            value = text,
            onValueChange = onTextChanged,
            label = {
                Text("Stateless")
            }
        )
    }
}

class MainViewModel : ViewModel() {

    var text by mutableStateOf("")

    fun onTextChanged(text: String) {
        viewModelScope.launch {
            delay(100L)
            this@MainViewModel.text = text
        }
    }
}