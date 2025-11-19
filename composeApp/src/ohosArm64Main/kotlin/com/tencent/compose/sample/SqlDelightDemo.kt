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
    var errorMessage by remember { mutableStateOf("") } // 添加错误消息状态

    LaunchedEffect(Unit) {
        withContext(Dispatchers.Default) {
            try {
                println("开始初始化数据库...")
                val driver = createTestDriver()
                println("数据库驱动创建成功")
                val db = MyDatabase(driver = driver)
                database = db
                println("数据库实例创建成功")

                // 加载所有人员
                println("开始加载人员数据...")
                persons = db.personQueries.selectAll().executeAsList()
                println("人员数据加载完成，共 ${persons.size} 条记录")

                nextId = (persons.maxOfOrNull { it.id } ?: 0L) + 1
                println("下一个ID设置为: $nextId")

            } catch (e: Exception) {
                println("数据库初始化失败: ${e.message}")
                e.printStackTrace() // 打印完整堆栈信息
                errorMessage = "数据库测试失败: ${e.message}" // 使用正确的变量名
            }
        }
    }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                modifier = Modifier.padding(8.dp),
                style = androidx.compose.material.MaterialTheme.typography.body1
            )
        }

        Button(onClick = {
            database?.let { db ->
                println("开始插入新人员: ID=$nextId, 姓名=Person $nextId")
                // 插入新人员
                db.personQueries.insertPerson(nextId, "Person $nextId", (18..40).random().toLong())
                nextId++

                // 重新加载列表
                persons = db.personQueries.selectAll().executeAsList()
                println("插入完成，当前总人数: ${persons.size}")
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
