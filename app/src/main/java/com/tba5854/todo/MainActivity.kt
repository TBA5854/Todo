@file:OptIn(ExperimentalMaterial3Api::class)

package com.tba5854.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.tba5854.todo.models.ToDoEntityModel
import com.tba5854.todo.ui.theme.TodoTheme
import androidx.activity.viewModels
import com.tba5854.todo.viewmodels.ToDoViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: com.tba5854.todo.viewmodels.ToDoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            TodoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )\
                    Main()
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

@Preview(showBackground = true)
@Composable
fun Main(viewModel: ToDoViewModel) {
    val todos by viewModel.listOfToDo.observe(emptyList<ToDoEntityModel>())
    TodoTheme {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text("To-Do List") },
                )
            },
            floatingActionButton = {
                Button(onClick = {}) {
                    Icon(Icons.Filled.Add, contentDescription = "Add a new todo")
                }
            }
            ) { innerPadding ->
            Column(
                modifier =Modifier.padding(innerPadding)
            ) {

            ToDoTile(
                ToDoEntityModel(
                    1,
                    "title1",
                    "description1",
                    false
                )
            )
                ToDoTile(
                    ToDoEntityModel(
                        1,
                        "title1",
                        "description1",
                        false
                    )
                )
            }
        }
    }
}

//@PreviewParameter(ToDoPreviewParameterProvider::class)
@Composable
fun ToDoTile(item: ToDoEntityModel){
    Row (
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically
//            .fillMaxSize()
    ){
        Checkbox(
            onCheckedChange = { /*TODO*/ },
            checked = item.isCompleted
        )
        Text(
            item.title,
//            te,
//            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(1f)
                .alignByBaseline()
//            fillMaxWidth()

        )
        Button(
            onClick = { /*TODO*/ }
        ) {
            Icon(Icons.Outlined.Delete, contentDescription =  "Delete Item Button" )
        }
    }
}