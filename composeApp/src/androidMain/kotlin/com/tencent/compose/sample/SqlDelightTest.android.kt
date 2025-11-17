package com.tencent.compose.sample

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.tencent.compose.applicationContext
import com.tencent.compose.db.MyDatabase

actual fun createTestDriver(): SqlDriver {
    // 使用应用上下文而不是测试框架的 InstrumentationRegistry
    return AndroidSqliteDriver(MyDatabase.Schema, applicationContext)
}
