@file:OptIn(ExperimentalMaterial3Api::class)

package com.tba5854.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tba5854.todo.models.ToDoEntityModel
import com.tba5854.todo.ui.theme.TodoTheme
import androidx.lifecycle.MutableLiveData

class MainActivity : ComponentActivity() {
    private val listOfToDo = MutableLiveData<List<ToDoEntityModel>>()

    private fun initTodos() {
        listOfToDo.value = listOf(
            ToDoEntityModel(1, "title1", "description1", false),
            ToDoEntityModel(2, "title2", "description2", true),
            ToDoEntityModel(3, "title3", "description3", false),
            ToDoEntityModel(4, "title4", "description4", true),
            ToDoEntityModel(5, "title5", "description5", false),
        )
    }

    private fun addNewToDo(newToDo: ToDoEntityModel) {
        val currentList = listOfToDo.value ?: emptyList()
        listOfToDo.value = currentList + newToDo
    }

    private fun toggleCompletionStatus(id: Long) {
        val currentList = listOfToDo.value ?: return
        listOfToDo.value = currentList.map { toDo ->
            if (toDo.id == id) toDo.copy(isCompleted = !toDo.isCompleted) else toDo
        }
    }

    private fun deleteToDo(id: Long) {
        val currentList = listOfToDo.value ?: return
        listOfToDo.value = currentList.filter { it.id != id }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        initTodos()
        setContent {

            TodoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val todos by listOfToDo.observeAsState(initial = emptyList())
                    Main(
                        todos = todos,
                        onToggle = { id -> toggleCompletionStatus(id) },
                        onDelete = { id -> deleteToDo(id) },
                        onAdd = {
                            val nextId = (todos.maxOfOrNull { it.id } ?: 0L) + 1L
                            addNewToDo(
                                ToDoEntityModel(
                                    id = nextId,
                                    title = "New ToDo $nextId",
                                    description = "Description $nextId",
                                    isCompleted = false
                                )
                            )
                        }
                    )
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

@Composable
fun Main(
    todos: List<ToDoEntityModel>,
    onToggle: (Long) -> Unit,
    onDelete: (Long) -> Unit,
    onAdd: () -> Unit,
) {
    TodoTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text("To-Do List") },
                )
            },
            floatingActionButton = {
                Button(onClick = onAdd) {
                    Icon(Icons.Filled.Add, contentDescription = "Add a new todo")
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                todos.forEach { item ->
                    ToDoTile(
                        item = item,
                        onToggle = onToggle,
                        onDelete = onDelete
                    )
                }
            }
        }
    }
}

@Composable
fun ToDoTile(
    item: ToDoEntityModel,
    onToggle: (Long) -> Unit,
    onDelete: (Long) -> Unit,
){
    Row (
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically
//            .fillMaxSize()
    ){
        Checkbox(
            onCheckedChange = { onToggle(item.id) },
            checked = item.isCompleted
        )
        Text(
            item.title,
            modifier = Modifier
                .weight(1f)
                .alignByBaseline()

        )
        Button(
            onClick = { onDelete(item.id) }
        ) {
            Icon(Icons.Outlined.Delete, contentDescription =  "Delete Item Button" )
        }
    }
}