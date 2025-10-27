package com.tencent.compose.sample

import app.cash.sqldelight.db.SqlDriver
import com.tencent.compose.db.MyDatabase
import com.tencent.compose.db.Person
import kotlinx.coroutines.runBlocking
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

// 添加 expect 函数声明
expect fun createTestDriver(): SqlDriver

class SqlDelightTest {
    private lateinit var database: MyDatabase
    lateinit var dbHelper: DatabaseHelper

    init {
        // 创建数据库驱动
        val driver = createTestDriver()
        database = MyDatabase(driver)
        dbHelper = DatabaseHelper(database.personQueries)
    }

    fun testInsertAndSelect() = runBlocking {
        // 清空之前的数据
        dbHelper.deleteAllPersons()

        // 插入一条记录
        dbHelper.insertPerson(1L, "张三", 25L)

        // 查询所有记录
        val allPeople = dbHelper.getAllPersonsList()

        // 验证插入的记录是否存在
        assertNotNull(allPeople.find { it.id == 1L })
        assertEquals("张三", allPeople.find { it.id == 1L }?.name)
        assertEquals(25L, allPeople.find { it.id == 1L }?.age)

        println("✅ SQLDelight 测试成功：插入和查询操作正常")
    }

    fun testUpdateAndDelete() = runBlocking {
        // 插入一条记录
        dbHelper.insertPerson(2L, "李四", 30L)

        // 更新记录
        dbHelper.updatePerson(2L, "王五", 35L)

        // 查询更新后的记录
        val updatedPerson = dbHelper.getPersonById(2L)
        assertNotNull(updatedPerson)
        assertEquals("王五", updatedPerson?.name)
        assertEquals(35L, updatedPerson?.age)

        // 删除记录
        dbHelper.deletePerson(2L)

        // 验证记录是否被删除
        assertNull(dbHelper.getPersonById(2L))

        println("✅ SQLDelight 测试成功：更新和删除操作正常")
    }

    fun testDeleteAll() = runBlocking {
        // 插入多条记录
        dbHelper.insertPerson(3L, "赵六", 28L)
        dbHelper.insertPerson(4L, "孙七", 32L)

        // 删除所有记录
        dbHelper.deleteAllPersons()

        // 验证所有记录是否被删除
        val allPeople = dbHelper.getAllPersonsList()
        assertTrue(allPeople.isEmpty())

        println("✅ SQLDelight 测试成功：删除所有记录操作正常")
    }
}
