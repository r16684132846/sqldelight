package app.cash.sqldelight.intellij.gotodeclaration

import app.cash.sqldelight.intellij.SqlDelightGotoDeclarationHandler
import app.cash.sqldelight.intellij.SqlDelightProjectTestCase
import com.alecstrong.sql.psi.core.psi.SqlIdentifier
import com.google.common.truth.Truth.assertThat
import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.psiUtil.getStrictParentOfType

class GoToDeclarationHandlerTest : SqlDelightProjectTestCase() {
  private val goToDeclarationHandler = SqlDelightGotoDeclarationHandler()

  fun testMethodGoesToIdentifier() {
    myFixture.openFileInEditor(
      tempRoot.findFileByRelativePath("src/main/java/com/example/SampleClass.java")!!,
    )
    val sourceElement = searchForElement<PsiElement>("someQuery").single()
    val elements = goToDeclarationHandler.getGotoDeclarationTargets(sourceElement, 0, editor)

    myFixture.openFileInEditor(
      tempRoot.findFileByRelativePath("src/main/sqldelight/com/example/Main.sq")!!,
    )
    val offset = file.text.indexOf("someQuery")
    assertThat(elements).asList().containsExactly(
      file.findElementAt(offset)!!.getStrictParentOfType<SqlIdentifier>(),
    )
  }

  fun testMethodGoesToIdentifierFromKotlin() {
    myFixture.openFileInEditor(
      tempRoot.findFileByRelativePath("src/main/kotlin/com/example/KotlinClass.kt")!!,
    )
    val sourceElement = searchForElement<PsiElement>("someQuery").single()
    val elements = goToDeclarationHandler.getGotoDeclarationTargets(sourceElement, 0, editor)

    myFixture.openFileInEditor(
      tempRoot.findFileByRelativePath("src/main/sqldelight/com/example/Main.sq")!!,
    )
    val offset = file.text.indexOf("someQuery")
    assertThat(elements).asList().containsExactly(
      file.findElementAt(offset)!!.getStrictParentOfType<SqlIdentifier>(),
    )
  }
}
