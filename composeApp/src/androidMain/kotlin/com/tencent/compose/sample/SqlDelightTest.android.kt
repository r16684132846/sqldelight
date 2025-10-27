package com.tencent.compose.sample

import android.app.Application
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.tencent.compose.db.MyDatabase

// 创建一个简单的测试 Application
class TestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}

actual fun createTestDriver(): SqlDriver {
    // 使用 ApplicationProvider 获取测试上下文
    val context = ApplicationProvider.getApplicationContext<Context>()
    return AndroidSqliteDriver(MyDatabase.Schema, context)
}
