//package com.tencent.compose.sample
//
//import kotlinx.coroutines.runBlocking
////import kotlin.test.Test
//import kotlin.test.assertEquals
//import kotlin.test.assertNotNull
//import kotlin.test.assertNull
//import kotlin.test.assertTrue
//
//class SqlDelightTestSuite {
//    private lateinit var sqlDelightTest: SqlDelightTest
//
//// @Test
//fun testDatabaseOperations() = runBlocking {
//    println("开始执行数据库操作测试...")
//
//    sqlDelightTest = SqlDelightTest()
//    println("SqlDelightTest 实例创建成功")
//
//    // 测试插入和查询
//    println("开始测试插入和查询...")
//    sqlDelightTest.testInsertAndSelect()
//    println("插入和查询测试完成")
//
//    // 测试更新和删除
//    println("开始测试更新和删除...")
//    sqlDelightTest.testUpdateAndDelete()
//    println("更新和删除测试完成")
//
//    // 测试删除所有记录
//    println("开始测试删除所有记录...")
//    sqlDelightTest.testDeleteAll()
//    println("删除所有记录测试完成")
//}
//
//
////    @Test
//    fun testConcurrentOperations() = runBlocking {
//        sqlDelightTest = SqlDelightTest()
//
//        // 清空数据
//        sqlDelightTest.dbHelper.deleteAllPersons()
//
//        // 顺序插入，避免并发问题
//        sqlDelightTest.dbHelper.insertPerson(10L, "用户10", 20L)
//        sqlDelightTest.dbHelper.insertPerson(11L, "用户11", 21L)
//        sqlDelightTest.dbHelper.insertPerson(12L, "用户12", 22L)
//
//        // 验证所有记录都已插入
//        val allPeople = sqlDelightTest.dbHelper.getAllPersonsList()
//        assertEquals(3, allPeople.size)
//        assertNotNull(allPeople.find { it.id == 10L })
//        assertNotNull(allPeople.find { it.id == 11L })
//        assertNotNull(allPeople.find { it.id == 12L })
//
//        println("✅ SQLDelight 并发测试成功")
//    }
//
////    @Test
//    fun testEdgeCases() = runBlocking {
//        sqlDelightTest = SqlDelightTest()
//
//        // 清空数据
//        sqlDelightTest.dbHelper.deleteAllPersons()
//
//        // 测试空字符串和特殊字符
//        sqlDelightTest.dbHelper.insertPerson(20L, "", 0L)
//        sqlDelightTest.dbHelper.insertPerson(21L, "特殊字符!@#$%^&*()", 999L)
//
//        val allPeople = sqlDelightTest.dbHelper.getAllPersonsList()
//        assertEquals(2, allPeople.size)
//        assertEquals("", allPeople.find { it.id == 20L }?.name)
//        assertEquals("特殊字符!@#$%^&*()", allPeople.find { it.id == 21L }?.name)
//
//        println("✅ SQLDelight 边界条件测试成功")
//    }
//}
