package com.tencent.compose.sample

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.tencent.compose.db.MyDatabase

actual fun createTestDriver(): SqlDriver {
    return try {
        NativeSqliteDriver(MyDatabase.Schema, "test.db")
    } catch (e: Exception) {
        throw RuntimeException("无法创建数据库驱动: ${e.message}")
    }
}
