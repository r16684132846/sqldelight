/*
 * Tencent is pleased to support the open source community by making ovCompose available.
 * Copyright (C) 2025 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tencent.compose.sample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tencent.compose.db.MyDatabase
import com.tencent.compose.db.Person
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
internal fun SqlDelightDemo() {
    var persons by remember { mutableStateOf<List<Person>>(emptyList()) }
    var database by remember { mutableStateOf<MyDatabase?>(null) }
    var nextId by remember { mutableStateOf(1L) }

    LaunchedEffect(Unit) {
        withContext(Dispatchers.Default) {
            try {
                val db = MyDatabase(
                    driver = createTestDriver()
                )
                database = db

                // 加载所有人员
                persons = db.personQueries.selectAll().executeAsList()
                nextId = (persons.maxOfOrNull { it.id } ?: 0L) + 1
            } catch (e: Exception) {
                println("数据库初始化失败: ${e.message}")
            }
        }
    }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Button(onClick = {
            database?.let { db ->
                // 插入新人员
                db.personQueries.insertPerson(nextId, "Person $nextId", (18..40).random().toLong())
                nextId++

                // 重新加载列表
                persons = db.personQueries.selectAll().executeAsList()
            }
        }) {
            Text("添加人员")
        }

        Button(onClick = {
            database?.let { db ->
                // 删除所有人员
                db.personQueries.deleteAll()

                // 重新加载列表
                persons = db.personQueries.selectAll().executeAsList()
            }
        }) {
            Text("清空所有")
        }

        LazyColumn {
            items(persons) { person ->
                Text("ID: ${person.id}, 姓名: ${person.name}, 年龄: ${person.age}")
            }
        }
    }
}
