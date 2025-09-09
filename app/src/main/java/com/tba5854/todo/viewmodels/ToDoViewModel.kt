package com.tba5854.todo.viewmodels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tba5854.todo.models.ToDoEntityModel

class ToDoViewModel:ViewModel() {
    var listOfToDo= MutableLiveData<List< ToDoEntityModel>>()



    fun init(){
        this.listOfToDo.value=listOf<ToDoEntityModel>(
            ToDoEntityModel(1,"title1","description1",false),
            ToDoEntityModel(2,"title2","description2",true),
            ToDoEntityModel(3,"title3","description3",false),
            ToDoEntityModel(4,"title4","description4",true),
            ToDoEntityModel(5,"title5","description5",false),
        )
    }

    fun getToDoList(): LiveData<List< ToDoEntityModel>> {
        return listOfToDo
    }

    fun addNewToDo(newToDo: ToDoEntityModel) {
        val currentList = listOfToDo.value ?: emptyList()
        val updatedList = currentList + newToDo
        listOfToDo.value = updatedList
    }

    fun toggleCompletionStatus(id: Long) {
        val currentList = listOfToDo.value ?: return
        val updatedList = currentList.map { toDo ->
            if (toDo.id == id) {
                toDo.copy(isCompleted = !toDo.isCompleted)
            } else {
                toDo
            }
        }
        listOfToDo.value = updatedList
    }

    fun deleteToDo(id: Long) {
        val currentList = listOfToDo.value ?: return
        val updatedList = currentList.filter { it.id != id }
        listOfToDo.value = updatedList
    }
}