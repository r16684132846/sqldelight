package com.tencent.compose.sample

import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.tencent.compose.db.MyDatabase
import com.tencent.compose.db.Person
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlin.time.TimeSource

// 创建单例数据库实例
private val database by lazy {
    val driver = NativeSqliteDriver(MyDatabase.Schema, "test.db")
    MyDatabase(driver)
}

actual fun insertsql(
    name: String,
    age: Int,
    email: String,
    callback: (err: Exception?, rowId: Long?) -> Unit
) {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            // 在SQLDelight中，id通常是自动生成的，但我们在这里显式传递它
            // 假设我们使用当前时间戳作为ID
            val id = TimeSource.Monotonic.markNow().elapsedNow().inWholeMilliseconds
            database.personQueries.insertPerson(id, name, age.toLong())
            callback(null, id)
        } catch (e: Exception) {
            callback(e, null)
        }
    }
}

actual fun querysql(callback: (err: Exception?, resultSet: Any?) -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val persons = database.personQueries.selectAll().executeAsList()
            callback(null, persons)
        } catch (e: Exception) {
            callback(e, null)
        }
    }
}

actual fun updatesql(
    id: Long,
    name: String,
    age: Int,
    email: String,
    callback: (err: Exception?, count: Int?) -> Unit
) {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            database.personQueries.updatePerson(name, age.toLong(), id)
            callback(null, 1) // 成功更新一行
        } catch (e: Exception) {
            callback(e, null)
        }
    }
}

actual fun deletesql(
    id: Long,
    callback: (err: Exception?, count: Int?) -> Unit
) {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            database.personQueries.deletePerson(id)
            callback(null, 1) // 成功删除一行
        } catch (e: Exception) {
            callback(e, null)
        }
    }
}

// 删除所有NAPI外部函数声明

actual fun processResultSet(
    resultSet: Any,
    callback: (List<Map<String, Any>>) -> Unit
) {
    if (resultSet is List<*>) {
        val result = resultSet.map { person ->
            if (person is Person) {
                mapOf(
                    "id" to person.id,
                    "name" to person.name,
                    "age" to person.age.toInt(),
                    "email" to ""
                )
            } else {
                emptyMap()
            }
        }
        callback(result.filter { it.isNotEmpty() })
    } else {
        callback(emptyList())
    }
}
