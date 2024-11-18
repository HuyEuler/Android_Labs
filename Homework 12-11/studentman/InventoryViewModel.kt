/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.inventory

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope

import com.example.inventory.data.Student
import com.example.inventory.data.StudentDao
import kotlinx.coroutines.launch
import java.util.Date

/**
 * View Model to keep a reference to the Inventory repository and an up-to-date list of all items.
 *
 */
class InventoryViewModel(private val itemDao: StudentDao) : ViewModel() {

    // Cache all items form the database using LiveData.
    val allItems: LiveData<List<Student>> = itemDao.getItems().asLiveData()

    /**
     * Updates an existing Item in the database.
     */
    fun updateItem(
        studentMSSV: Int,
        studentName: String,
        studentBirth: String,
        studentLive: String
    ) {
        val updatedItem = getUpdatedItemEntry(studentMSSV, studentName, studentBirth, studentLive)
        updateItem(updatedItem)
    }


    /**
     * Launching a new coroutine to update an item in a non-blocking way
     */
    private fun updateItem(item: Student) {
        viewModelScope.launch {
            itemDao.update(item)
        }
    }

    /**
     * Decreases the stock by one unit and updates the database.
     */

    /**
     * Inserts the new Item into database.
     */
    fun addNewItem(studentMSSV: Int,studentName: String, studentBirth: String, studentLive: String) {
        val newItem = getNewItemEntry(studentMSSV, studentName, studentBirth,studentLive)
        insertItem(newItem)
    }

    /**
     * Launching a new coroutine to insert an item in a non-blocking way
     */
    private fun insertItem(item: Student) {
        viewModelScope.launch {
            itemDao.insert(item)
        }
    }

    /**
     * Launching a new coroutine to delete an item in a non-blocking way
     */
    fun deleteItem(item: Student) {
        viewModelScope.launch {
            itemDao.delete(item)
        }
    }

    /**
     * Retrieve an item from the repository.
     */
    fun retrieveItem(id: Int): LiveData<Student> {
        return itemDao.getItem(id).asLiveData()
    }

    /**
     * Returns true if the EditTexts are not empty
     */
    fun isEntryValid(studentMSSV: Int,studentName: String, studentBirth: String, studentLive: String): Boolean {
        if (studentName.isBlank() || studentBirth.isBlank() || studentLive.isBlank() ) {
            return false
        }
        return true
    }

    /**
     * Returns an instance of the [Item] entity class with the item info entered by the user.
     * This will be used to add a new entry to the Inventory database.
     */
    private fun getNewItemEntry(studentMSSV: Int, studentName: String, studentBirth: String, studentLive: String): Student {
        return Student(
            mssv = studentMSSV,
            hoten = studentName,
            ngaysinh = studentBirth,
            quequan = studentLive
        )
    }

    /**
     * Called to update an existing entry in the Inventory database.
     * Returns an instance of the [Item] entity class with the item info updated by the user.
     */
    private fun getUpdatedItemEntry(
        studentMSSV: Int,
        studentName: String,
        studentBirth: String,
        studentLive: String
    ): Student {
        return Student(
            mssv = studentMSSV,
            hoten = studentName,
            ngaysinh = studentBirth,
            quequan = studentLive
        )
    }
}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class InventoryViewModelFactory(private val studentDao: StudentDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InventoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return InventoryViewModel(studentDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

