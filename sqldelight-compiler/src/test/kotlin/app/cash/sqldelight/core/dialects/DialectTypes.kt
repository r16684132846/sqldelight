package app.cash.sqldelight.core.dialects

import app.cash.sqldelight.core.TestDialect
import app.cash.sqldelight.core.TestDialect.HSQL
import app.cash.sqldelight.core.TestDialect.MYSQL
import app.cash.sqldelight.core.TestDialect.POSTGRESQL
import app.cash.sqldelight.core.TestDialect.SQLITE_3_18
import app.cash.sqldelight.core.TestDialect.SQLITE_3_24
import app.cash.sqldelight.core.TestDialect.SQLITE_3_25
import app.cash.sqldelight.core.TestDialect.SQLITE_3_30
import app.cash.sqldelight.core.TestDialect.SQLITE_3_35
import app.cash.sqldelight.core.TestDialect.SQLITE_3_38
import app.cash.sqldelight.dialects.hsql.HsqlDialect
import app.cash.sqldelight.dialects.mysql.MySqlDialect
import app.cash.sqldelight.dialects.postgresql.PostgreSqlDialect
import com.squareup.kotlinpoet.INT
import com.squareup.kotlinpoet.LONG

internal val TestDialect.textType
  get() = when (this) {
    MYSQL, SQLITE_3_24, SQLITE_3_18, SQLITE_3_25, SQLITE_3_30, SQLITE_3_35, SQLITE_3_38, POSTGRESQL -> "TEXT"
    HSQL -> "VARCHAR(8)"
  }

internal val TestDialect.blobType
  get() = when (this) {
    MYSQL, SQLITE_3_24, SQLITE_3_18, SQLITE_3_25, SQLITE_3_30, SQLITE_3_35, SQLITE_3_38, HSQL -> "BLOB"
    POSTGRESQL -> "BYTEA"
  }

internal val TestDialect.intType
  get() = when (this) {
    MYSQL, SQLITE_3_24, SQLITE_3_18, SQLITE_3_25, SQLITE_3_30, SQLITE_3_35, SQLITE_3_38, POSTGRESQL, HSQL -> "INTEGER"
  }

internal val TestDialect.intKotlinType
  get() = when (this) {
    SQLITE_3_24, SQLITE_3_18, SQLITE_3_25, SQLITE_3_30, SQLITE_3_35, SQLITE_3_38 -> LONG
    MYSQL, POSTGRESQL, HSQL -> INT
  }

/**
 * The [check] statement generated for the prepared statement type in the binder lambda for this dialect.
 *
 * See [QueryGenerator].
 */
internal val TestDialect.binderCheck
  get() = when {
    dialect.isSqlite -> ""
    else -> when (dialect) {
      is PostgreSqlDialect, is HsqlDialect, is MySqlDialect -> "check(this is app.cash.sqldelight.driver.jdbc.JdbcPreparedStatement)\n        "
      else -> throw IllegalStateException("Unknown dialect: $this")
    }
  }

/**
 * The [check] statement generated for the cursor type in the mapper lambda for this dialect.
 *
 * See [SelectQueryGenerator].
 */
internal fun TestDialect.cursorCheck(whitespaces: Int) = when {
  dialect.isSqlite -> ""
  else -> when (dialect) {
    is PostgreSqlDialect, is HsqlDialect, is MySqlDialect -> "check(cursor is app.cash.sqldelight.driver.jdbc.JdbcCursor)\n${" ".repeat(whitespaces)}"
    else -> throw IllegalStateException("Unknown dialect: $this")
  }
}
