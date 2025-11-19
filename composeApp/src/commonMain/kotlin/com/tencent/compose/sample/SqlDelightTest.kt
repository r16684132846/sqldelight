package com.tencent.compose.sample

import app.cash.sqldelight.db.SqlDriver
import com.tencent.compose.db.MyDatabase
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
        try {
            val driver = createTestDriver()
            database = MyDatabase(driver)
            dbHelper = DatabaseHelper(database.personQueries)
            println("Database-${database}、dbHelper-${dbHelper} Database opened successfully")
        } catch (e: Exception) {
            println("Failed to open database: ${e.message}")
            throw e
        }
    }

    fun testInsertAndSelect() = runBlocking {
        println("开始执行插入和查询测试...")
        // 清空之前的数据
        println("开始清空数据...")
        dbHelper.deleteAllPersons()
        println("数据清空完成")

        // 插入一条记录
        println("开始插入数据...")
        dbHelper.insertPerson(1L, "张三", 25L)
        println("数据插入完成")

        // 查询所有记录
        println("开始查询数据...")
        val allPeople = dbHelper.getAllPersonsList()
        println("查询到的数据: $allPeople")

        // 验证插入的记录是否存在
        assertNotNull(allPeople.find { it.id == 1L })
        assertEquals("张三", allPeople.find { it.id == 1L }?.name)
        assertEquals(25L, allPeople.find { it.id == 1L }?.age)

        println("✅ SQLDelight 测试成功：插入和查询操作正常")
    }

    fun testUpdateAndDelete() = runBlocking {
        // 插入一条记录
        println("开始插入测试数据...")
        dbHelper.insertPerson(2L, "李四", 30L)
        println("测试数据插入完成")

        // 更新记录
        println("开始更新数据...")
        dbHelper.updatePerson(2L, "王五", 35L)
        println("数据更新完成")

        // 查询更新后的记录
        println("开始查询更新后的数据...")
        val updatedPerson = dbHelper.getPersonById(2L)
        println("查询到的更新后数据: $updatedPerson")

        assertNotNull(updatedPerson)
        assertEquals("王五", updatedPerson?.name)
        assertEquals(35L, updatedPerson?.age)

        // 删除记录
        println("开始删除数据...")
        dbHelper.deletePerson(2L)
        println("数据删除完成")

        // 验证记录是否被删除
        println("开始验证删除结果...")
        assertNull(dbHelper.getPersonById(2L))

        println("✅ SQLDelight 测试成功：更新和删除操作正常")
    }

    fun testDeleteAll() = runBlocking {
        // 插入多条记录
        println("开始插入多条测试数据...")
        dbHelper.insertPerson(3L, "赵六", 28L)
        dbHelper.insertPerson(4L, "孙七", 32L)
        println("多条测试数据插入完成")

        // 删除所有记录
        println("开始删除所有记录...")
        dbHelper.deleteAllPersons()
        println("所有记录删除完成")

        // 验证所有记录是否被删除
        println("开始验证删除结果...")
        val allPeople = dbHelper.getAllPersonsList()
        assertTrue(allPeople.isEmpty())

        println("✅ SQLDelight 测试成功：删除所有记录操作正常")
    }
}
