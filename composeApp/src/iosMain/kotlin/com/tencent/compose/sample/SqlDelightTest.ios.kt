package com.tencent.compose.sample

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.tencent.compose.db.MyDatabase

actual fun createTestDriver(): SqlDriver {
    return NativeSqliteDriver(MyDatabase.Schema, "test.db")
}
