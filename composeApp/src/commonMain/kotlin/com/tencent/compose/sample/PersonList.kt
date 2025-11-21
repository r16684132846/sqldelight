//package com.tencent.compose.sample
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import com.tencent.compose.db.MyDatabase
//import com.tencent.compose.db.Person
//import kotlinx.coroutines.launch
//
//@Composable
//fun PersonListScreen() {
//    // 初始化数据库
//    val driver = remember { createTestDriver() }
//    val database = remember { MyDatabase(driver) }
//
//    val dbHelper = remember { DatabaseHelper(database.personQueries) }
//    var persons by remember { mutableStateOf<List<Person>>(emptyList()) }
//    val coroutineScope = rememberCoroutineScope()
//
//    // 加载数据
//    LaunchedEffect(Unit) {
//        coroutineScope.launch {
//            persons = dbHelper.getAllPersonsList()
//            println("获取已加载所有人员数据:persons-$persons")
//        }
//    }
//
//    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
//        Text("SQLDelight 数据库示例", style = MaterialTheme.typography.h5)
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Row {
//            Button(onClick = {
//                coroutineScope.launch {
//                    // 添加示例数据
//                    val nextId = (persons.maxOfOrNull { it.id } ?: 0) + 1
//                    dbHelper.insertPerson(nextId, "测试用户$nextId", (20 + nextId).toLong())
//                    persons = dbHelper.getAllPersonsList()
//                }
//            }) {
//                Text("添加用户")
//            }
//
//            Spacer(modifier = Modifier.width(8.dp))
//
//            Button(onClick = {
//                coroutineScope.launch {
//                    dbHelper.deleteAllPersons()
//                    persons = dbHelper.getAllPersonsList()
//                }
//            }) {
//                Text("清空所有")
//            }
//
//            Spacer(modifier = Modifier.width(8.dp))
//
//            Button(onClick = {
//                coroutineScope.launch {
//                    val testRunner = SqlDelightTestRunner()
//                    testRunner.runAllTests()
//                }
//            }) {
//                Text("运行测试")
//            }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        LazyColumn {
//            items(persons) { person ->
//                Card(
//                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
//                    elevation = 4.dp
//                ) {
//                    Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
//                        Column {
//                            Text("ID: ${person.id}", style = MaterialTheme.typography.body1)
//                            Text("姓名: ${person.name}", style = MaterialTheme.typography.body1)
//                            Text("年龄: ${person.age}", style = MaterialTheme.typography.body1)
//                        }
//
//                        Spacer(Modifier.weight(1f))
//
//                        Button(onClick = {
//                            coroutineScope.launch {
//                                dbHelper.deletePerson(person.id)
//                                persons = dbHelper.getAllPersonsList()
//                            }
//                        }) {
//                            Text("删除")
//                        }
//                    }
//                }
//            }
//
//            if (persons.isEmpty()) {
//                item {
//                    Text("暂无数据", modifier = Modifier.fillMaxWidth().padding(16.dp))
//                }
//            }
//        }
//    }
//}
