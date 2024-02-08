package com.example.rickmasterstestdev.ui.doors.widgets.components

import android.widget.Toast
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickmasterstestdev.ui.doors.DoorsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreenWidget(
    doorId: Int,
    navigateBack: () -> Unit,
    doorsViewModel: DoorsViewModel = hiltViewModel()
) {
    val originalName by doorsViewModel.doorName.collectAsState()
    var newName by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current

    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = true) {
        doorsViewModel.getDoor(doorId)
    }
    LaunchedEffect(key1 = originalName) {
        newName = originalName
    }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }
    AlertDialog(
        onDismissRequest = { navigateBack() },
        title = { Text(text = "Название двери") },
        text = {
            TextField(
                value = newName,
                onValueChange = { newName = it },
                singleLine = true,
                modifier = Modifier.focusRequester(focusRequester)
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    if(newName.trim().isEmpty()) {
                        Toast.makeText(context, "Text cannot be empty!", Toast.LENGTH_SHORT).show()
                    } else {
                        doorsViewModel.renameDoor(doorId, newName)
                        navigateBack()
                    }
                }

            ) {
                Text("Переименовать")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    navigateBack()
                }
            ) {
                Text("Отмена")
            }
        }
    )
}
