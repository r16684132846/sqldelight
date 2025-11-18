#include "libkn_api.h"

extern "C" {
libkn_kref_app_cash_sqldelight_db_SqlDriver createTestDriver() {
    // 直接调用 Kotlin 层的实现，避免在 Native 层处理路径问题
    auto symbols = libkn_symbols();
    return symbols->kotlin.root.com.tencent.compose.sample.createTestDriver();
}
}
