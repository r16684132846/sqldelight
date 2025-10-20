package app.cash.sqldelight.intellij.lang

import app.cash.sqldelight.core.lang.SqlDelightFile
import app.cash.sqldelight.core.lang.SqlDelightFileType
import app.cash.sqldelight.core.lang.psi.ImportStmtMixin
import app.cash.sqldelight.core.lang.util.findChildOfType
import app.cash.sqldelight.core.lang.util.findChildrenOfType
import app.cash.sqldelight.core.psi.SqlDelightImportStmtList
import app.cash.sqldelight.intellij.inspections.columnJavaTypes
import com.intellij.lang.ImportOptimizer
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiFileFactory

class SqlDelightImportOptimizer : ImportOptimizer {

  override fun supports(file: PsiFile): Boolean = file is SqlDelightFile

  override fun processFile(file: PsiFile): Runnable = Runnable {
    val manager = PsiDocumentManager.getInstance(file.project)
    val document = manager.getDocument(file) ?: return@Runnable
    manager.commitDocument(document)

    val javaTypes = file.columnJavaTypes()

    val remainingImports = file.findChildrenOfType<ImportStmtMixin>()
      .asSequence()
      .map { import -> import.text }
      .filter { import -> import.substringAfterLast(".").removeSuffix(";") in javaTypes }
      .sorted()
      .joinToString("\n")
    val factory = PsiFileFactory.getInstance(file.project)
    val dummyFile = factory.createFileFromText(
      "_Dummy_.${app.cash.sqldelight.core.lang.SQLDELIGHT_EXTENSION}",
      SqlDelightFileType,
      remainingImports,
    )
    val newImportList = dummyFile.findChildOfType<SqlDelightImportStmtList>()
    if (newImportList != null) {
      file.findChildOfType<SqlDelightImportStmtList>()?.replace(newImportList)
    }
  }
}
