package com.tencent.compose.sample

actual fun querysql(callback: (err: Exception?, resultSet: Any?) -> Unit) {
}

actual fun updatesql(
    id: Long,
    name: String,
    age: Int,
    email: String,
    callback: (err: Exception?, count: Int?) -> Unit
) {
}

actual fun deletesql(
    id: Long,
    callback: (err: Exception?, count: Int?) -> Unit
) {
}

actual fun insertsql(
    name: String,
    age: Int,
    email: String,
    callback: (err: Exception?, rowId: Long?) -> Unit
) {
}

actual fun processResultSet(
    resultSet: Any,
    callback: (List<Map<String, Any>>) -> Unit
) {
}