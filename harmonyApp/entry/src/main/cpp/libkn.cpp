#include "libkn_api.h"
#include <cstdlib>
#include <cstring>
#include <libgen.h>
#include <string>
#include <sys/stat.h>
#include <unistd.h>

// 定义全局数据库路径变量
std::string g_dbPath = "";

// 创建目录的辅助函数
int createDirectories(const char *path) {
    char *temp = strdup(path);
    char *dir = dirname(temp);

    // 检查目录是否存在，如果不存在则创建
    struct stat st = {0};
    if (stat(dir, &st) == -1) {
        // 递归创建父目录
        char *parent = strdup(dir);
        char *parentDir = dirname(parent);
        if (strcmp(parentDir, ".") != 0 && strcmp(parentDir, "/") != 0 && strcmp(parentDir, dir) != 0) { // 防止无限递归
            createDirectories(parentDir);
        }
        int result = mkdir(dir, 0755);
        free(parent);
        free(temp);
        return result;
    }
    free(temp);
    return 0;
}

extern "C" {
libkn_kref_app_cash_sqldelight_db_SqlDriver createTestDriver() {
    // 使用从HarmonyOS传入的正确数据库路径
    std::string dbPath = g_dbPath.empty() ? "/data/storage/el2/base/haps/entry/files/my_database.db" : g_dbPath;
    printf("数据库Database path: %s\n", dbPath.c_str());

    // 创建 SQLite 驱动
    auto kt = libkn_symbols();
    printf("创建SQLite驱动...\n", kt);

    // 获取数据库 Schema
    auto schema = kt->kotlin.root.com.tencent.compose.db.MyDatabase.Companion.get_Schema(
        kt->kotlin.root.com.tencent.compose.db.MyDatabase.Companion._instance());
    printf("获取数据库Schema...\n", schema);
    // 调用Kotlin中定义的createTestDriver函数
    auto driver = kt->kotlin.root.com.tencent.compose.sample.createTestDriver();
    printf("创建数据库驱动成功...\n", driver);
    return driver;
}

// 添加设置数据库路径
void setDatabasePath(const char *path) {
    if (path != nullptr) {
        g_dbPath = std::string(path);
        printf("设置数据库路径成功: %s\n", path);
    }
}

// 添加获取数据库路径
const char *getDatabasePath() {
    return g_dbPath.c_str(); // 返回数据库路径
    printf("获取数据库路径成功: %s\n", g_dbPath.c_str());
}
}
