package app.cash.sqldelight.core.compiler

import app.cash.sqldelight.core.SqlDelightFileIndex
import app.cash.sqldelight.core.lang.ASYNC_RESULT_TYPE
import app.cash.sqldelight.core.lang.DATABASE_SCHEMA_TYPE
import app.cash.sqldelight.core.lang.VALUE_RESULT_TYPE
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asTypeName
import com.squareup.kotlinpoet.joinToCode
import kotlin.reflect.KClass

internal class DatabaseExposerGenerator(
  val implementation: TypeSpec,
  val fileIndex: SqlDelightFileIndex,
  val generateAsync: Boolean,
) {
  private val interfaceType = ClassName(fileIndex.packageName, fileIndex.className)

  fun exposedSchema(): PropertySpec {
    return PropertySpec.builder(
      "schema",
      DATABASE_SCHEMA_TYPE.parameterizedBy(
        (if (generateAsync) ASYNC_RESULT_TYPE else VALUE_RESULT_TYPE)
          .parameterizedBy(Unit::class.asTypeName()),
      ),
    )
      .addModifiers(KModifier.INTERNAL)
      .receiver(KClass::class.asTypeName().parameterizedBy(interfaceType))
      .getter(
        FunSpec.getterBuilder()
          .addStatement("return %N.Schema", implementation)
          .build(),
      )
      .build()
  }

  fun exposedConstructor(): FunSpec {
    return implementation.primaryConstructor!!.let { constructor ->
      FunSpec.builder("newInstance")
        .addModifiers(KModifier.INTERNAL)
        .returns(ClassName(fileIndex.packageName, fileIndex.className))
        .receiver(KClass::class.asTypeName().parameterizedBy(interfaceType))
        .addParameters(constructor.parameters)
        .addStatement("return %N(%L)", implementation, constructor.parameters.map { CodeBlock.of("%N", it) }.joinToCode(", "))
        .build()
    }
  }
}
