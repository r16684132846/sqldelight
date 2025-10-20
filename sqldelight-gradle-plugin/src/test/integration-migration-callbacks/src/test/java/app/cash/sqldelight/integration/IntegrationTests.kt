package app.cash.sqldelight.integration

import app.cash.sqldelight.db.AfterVersion
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver.Companion.IN_MEMORY
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class IntegrationTests {
  @Test fun migrationCallbacks() {
    val database = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
    QueryWrapper.Schema.migrate(
      driver = database,
      oldVersion = 0,
      newVersion = QueryWrapper.Schema.version,
      AfterVersion(1) { it.execute(null, "INSERT INTO test (value) VALUES('hello')", 0) },
      AfterVersion(2) { it.execute(null, "INSERT INTO test2 (value, value2) VALUES('hello2', 'sup2')", 0) },
      AfterVersion(3) { it.execute(null, "INSERT INTO test2 (new_value, new_value2) VALUES('hello3', 'sup3')", 0) },
    )

    val queryWrapper = QueryWrapper(database)
    val values = queryWrapper.testQueries.selectAll().executeAsList()
    assertThat(values).containsExactly(
      Test2(new_value = "hello", new_value2 = "sup"),
      Test2(new_value = "hello2", new_value2 = "sup2"),
      Test2(new_value = "hello3", new_value2 = "sup3"),
    )
  }

  @Test fun migrationCallbackLessThanOldVersionNotCalled() {
    val database = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
    QueryWrapper.Schema.migrate(
      driver = database,
      oldVersion = 0,
      newVersion = 2,
    )
    QueryWrapper.Schema.migrate(
      driver = database,
      oldVersion = 2,
      newVersion = QueryWrapper.Schema.version,
      AfterVersion(1) { it.execute(null, "INSERT INTO test (value) VALUES('hello')", 0) },
      AfterVersion(2) { it.execute(null, "INSERT INTO test2 (value, value2) VALUES('hello2', 'sup2')", 0) },
      AfterVersion(3) { it.execute(null, "INSERT INTO test2 (new_value, new_value2) VALUES('hello3', 'sup3')", 0) },
    )

    val queryWrapper = QueryWrapper(database)
    val values = queryWrapper.testQueries.selectAll().executeAsList()
    assertThat(values).containsExactly(
      Test2(new_value = "hello2", new_value2 = "sup2"),
      Test2(new_value = "hello3", new_value2 = "sup3"),
    )
  }

  @Test fun migrationCallbackGreaterThanNewVersionNotCalled() {
    val database = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
    QueryWrapper.Schema.migrate(
      driver = database,
      oldVersion = 0,
      newVersion = QueryWrapper.Schema.version,
      AfterVersion(1) { it.execute(null, "INSERT INTO test (value) VALUES('hello')", 0) },
      AfterVersion(2) { it.execute(null, "INSERT INTO test2 (value, value2) VALUES('hello2', 'sup2')", 0) },
      AfterVersion(3) { it.execute(null, "INSERT INTO test2 (new_value, new_value2) VALUES('hello3', 'sup3')", 0) },
      AfterVersion(4) { it.execute(null, "INSERT INTO test2 (new_value, new_value2) VALUES('hello4', 'sup4')", 0) },
    )

    val queryWrapper = QueryWrapper(database)
    val values = queryWrapper.testQueries.selectAll().executeAsList()
    assertThat(values).containsExactly(
      Test2(new_value = "hello", new_value2 = "sup"),
      Test2(new_value = "hello2", new_value2 = "sup2"),
      Test2(new_value = "hello3", new_value2 = "sup3"),
    )
  }
}
