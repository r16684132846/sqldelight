package app.cash.sqldelight.intellij

import app.cash.sqldelight.core.lang.SqlDelightFileType
import com.google.common.truth.Truth.assertThat
import com.intellij.testFramework.utils.parameterInfo.MockCreateParameterInfoContext

class SqlDelightParameterInfoHandlerTest : SqlDelightFixtureTestCase() {

  fun testParameterInfo() {
    myFixture.configureByText(
      SqlDelightFileType,
      """
        |CREATE TABLE example (
        |  exampleId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
        |  name TEXT NOT NULL,
        |  description TEXT
        |);
        |
        |insert:
        |INSERT INTO example (name, description)
        |VALUES (<caret>?, ?);
      """.trimMargin(),
    )

    val parameterInfoHandler = SqlDelightParameterInfoHandler()
    val context = MockCreateParameterInfoContext(editor, file)
    val element = parameterInfoHandler.findElementForParameterInfo(context)
    assertThat(element).isNotNull()
    val itemsToShow = context.itemsToShow
    assertThat(itemsToShow).isNotNull()
    assertThat(itemsToShow!![0]).isEqualTo(listOf("name: TEXT", "description: TEXT"))
  }
}
