/*
 * Copyright (C) 2018 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package app.cash.sqldelight.intellij

import app.cash.sqldelight.core.SqlDelightCompilationUnit
import app.cash.sqldelight.core.SqlDelightDatabaseProperties
import app.cash.sqldelight.core.SqlDelightFileIndex
import app.cash.sqldelight.core.SqlDelightSourceFolder
import app.cash.sqldelight.core.lang.SqlDelightFile
import app.cash.sqldelight.intellij.util.isAncestorOf
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiManager
import com.intellij.psi.util.PsiTreeUtil
import java.io.File

class FileIndex(
  private val properties: SqlDelightDatabaseProperties,
  override val contentRoot: VirtualFile =
    LocalFileSystem.getInstance().findFileByPath(properties.rootDirectory.absolutePath)!!,
) : SqlDelightFileIndex {
  override val isConfigured = true
  override val packageName = properties.packageName
  override val className = properties.className
  override val dependencies = properties.dependencies
  override val deriveSchemaFromMigrations = properties.deriveSchemaFromMigrations

  override fun packageName(file: SqlDelightFile): String {
    val original = if (file.parent == null) {
      file.originalFile as SqlDelightFile
    } else {
      file
    }
    val folder = sourceFolders(original, includeDependencies = false)
      .firstOrNull { PsiTreeUtil.isAncestor(it, original, false) } ?: return ""
    val folderPath = folder.virtualFile.path
    val filePath = original.virtualFile!!.path
    val startIndex = folderPath.length + 1
    try {
      return filePath.substring(startIndex, filePath.indexOf(original.name, startIndex) - 1).replace('/', '.')
    } catch (e: StringIndexOutOfBoundsException) {
      throw IllegalStateException("Error finding package name of $filePath in $folderPath", e)
    }
  }

  override fun outputDirectory(file: SqlDelightFile): List<String> {
    val vfile = file.virtualFile ?: return emptyList()
    val compilationUnits = properties.compilationUnits.filter { compilationUnit ->
      compilationUnit.sourceFolders.any { it.folder.localVirtualFile()?.isAncestorOf(vfile) == true }
    }
    return compilationUnits.map { it.outputDirectoryPath }.distinct()
  }

  override fun outputDirectories(): List<String> {
    return properties.compilationUnits.map { it.outputDirectoryPath }.distinct()
  }

  private val SqlDelightCompilationUnit.outputDirectoryPath get() =
    outputDirectoryFile.relativeTo(File(contentRoot.path))
      .path.replace(File.separatorChar, '/').trimEnd('/')

  override fun sourceFolders(
    file: VirtualFile,
    includeDependencies: Boolean,
  ): Collection<VirtualFile> {
    return sourceFolders(includeDependencies)
      .map { sourceFolders -> sourceFolders.mapNotNull { sourceFolder -> sourceFolder.folder.localVirtualFile() } }
      .fold(emptySet()) { currentSources: Collection<VirtualFile>, sourceSet ->
        if (sourceSet.any { it.isAncestorOf(file) }) {
          // File is in this source set.
          if (currentSources.isEmpty()) {
            return@fold sourceSet
          } else {
            // File also in another source set! The files available sources is the intersection.
            return@fold currentSources.intersect(sourceSet)
          }
        }
        return@fold currentSources
      }
  }

  override fun sourceFolders(
    file: SqlDelightFile,
    includeDependencies: Boolean,
  ): Collection<PsiDirectory> {
    return sourceFolders(file.virtualFile!!, includeDependencies)
      .map { PsiManager.getInstance(file.project).findDirectory(it)!! }
  }

  override fun sourceFolders(includeDependencies: Boolean): List<List<SqlDelightSourceFolder>> {
    return properties.compilationUnits.map {
      it.sourceFolders.filter { includeDependencies || !it.dependency }
    }
  }

  private fun File.localVirtualFile() = contentRoot.findFileByRelativePath(
    this.relativeTo(
      File(contentRoot.path),
    ).path.replace(File.separatorChar, '/').trimEnd('/'),
  )
}
