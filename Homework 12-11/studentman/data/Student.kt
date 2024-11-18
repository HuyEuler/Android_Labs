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
package com.example.studentman

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.NumberFormat
import java.util.Date

/**
 * Entity data class represents a single row in the database.
 */
@Entity
data class Student(
    @PrimaryKey(autoGenerate = true)
    val mssv: Int = 0,
    @ColumnInfo(name = "Họ tên")
    val hoten: String,
    @ColumnInfo(name = "Ngày sinh")
    val ngaysinh: String,
    @ColumnInfo(name = "Quê quán")
    val quequan: String,
)
/**
 * Returns the passed in price in currency format.
 */
