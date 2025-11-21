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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
internal fun SqlDelightTest() {
    var status by remember { mutableStateOf("就绪") }
    var people by remember { mutableStateOf<List<Map<String, Any>>>(emptyList()) }

    var idInput by remember { mutableStateOf("") }
    var nameInput by remember { mutableStateOf("") }
    var ageInput by remember { mutableStateOf("") }
    var emailInput by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("鸿蒙原生数据库操作演示")

        OutlinedTextField(
            value = idInput,
            onValueChange = { idInput = it },
            label = { Text("ID (仅更新/删除时需要)") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = nameInput,
            onValueChange = { nameInput = it },
            label = { Text("姓名") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = ageInput,
            onValueChange = { ageInput = it },
            label = { Text("年龄") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = emailInput,
            onValueChange = { emailInput = it },
            label = { Text("邮箱") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        try {
                            if (nameInput.isNotBlank() && ageInput.isNotBlank() && emailInput.isNotBlank()) {
                                val age = ageInput.toIntOrNull()
                                if (age != null) {
                                    insertsql(nameInput, age, emailInput) { err, rowId ->
                                        if (err != null) {
                                            status = "插入失败: ${err.message}"
                                        } else {
                                            status = "插入成功，行ID: $rowId"
                                            // 清空输入框
                                            nameInput = ""
                                            ageInput = ""
                                            emailInput = ""
                                            // 刷新列表
                                            querysql { _, resultSet ->
                                                if (resultSet != null) {
                                                    processResultSet(resultSet) { list ->
                                                        people = list
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    status = "请输入有效的年龄"
                                }
                            } else {
                                status = "请填写所有字段"
                            }
                        } catch (e: Exception) {
                            status = "插入异常: ${e.message}"
                        }
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("增加")
            }

            Button(
                onClick = {
                    coroutineScope.launch {
                        try {
                            val id = idInput.toLongOrNull()
                            val age = ageInput.toIntOrNull()
                            if (id != null && nameInput.isNotBlank() && age != null && emailInput.isNotBlank()) {
                                updatesql(id, nameInput, age, emailInput) { err, count ->
                                    if (err != null) {
                                        status = "更新失败: ${err.message}"
                                    } else {
                                        status = "更新成功，影响行数: $count"
                                        // 刷新列表
                                        querysql { _, resultSet ->
                                            if (resultSet != null) {
                                                processResultSet(resultSet) { list ->
                                                    people = list
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                status = "请输入有效ID和完整信息"
                            }
                        } catch (e: Exception) {
                            status = "更新异常: ${e.message}"
                        }
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("修改")
            }

            Button(
                onClick = {
                    coroutineScope.launch {
                        try {
                            val id = idInput.toLongOrNull()
                            if (id != null) {
                                deletesql(id) { err, count ->
                                    if (err != null) {
                                        status = "删除失败: ${err.message}"
                                    } else {
                                        status = "删除成功，影响行数: $count"
                                        // 清空输入框
                                        idInput = ""
                                        // 刷新列表
                                        querysql { _, resultSet ->
                                            if (resultSet != null) {
                                                processResultSet(resultSet) { list ->
                                                    people = list
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                status = "请输入有效ID"
                            }
                        } catch (e: Exception) {
                            status = "删除异常: ${e.message}"
                        }
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("删除")
            }

            Button(
                onClick = {
                    coroutineScope.launch {
                        querysql() { _, resultSet ->
                            if (resultSet != null) {
                                processResultSet(resultSet) { list ->
                                    people = list
                                    status = "查询完成，共${list.size}条记录"
                                }
                            }
                        }
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("查询")
            }
        }

        Spacer(Modifier.height(8.dp))
        Text("状态: $status")

        LazyColumn {
            items(people) { person ->
                PersonItem(person)
            }
        }
    }
}

// 处理结果集的平台相关函数
expect fun processResultSet(resultSet: Any, callback: (List<Map<String, Any>>) -> Unit)

// 插入新记录
expect fun insertsql(
    name: String,
    age: Int,
    email: String,
    callback: (err: Exception?, rowId: Long?) -> Unit
)

// 查询所有记录
expect fun querysql(
    callback: (err: Exception?, resultSet: Any?) -> Unit
)

// 更新记录
expect fun updatesql(
    id: Long,
    name: String,
    age: Int,
    email: String,
    callback: (err: Exception?, count: Int?) -> Unit
)

// 删除记录
expect fun deletesql(
    id: Long,
    callback: (err: Exception?, count: Int?) -> Unit
)

@Composable
private fun PersonItem(person: Map<String, Any>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text("ID: ${person["id"]}")
        Text("姓名: ${person["name"]}")
        Text("年龄: ${person["age"]}")
        Text("邮箱: ${person["email"]}")
        Spacer(Modifier.height(4.dp))
    }
}
