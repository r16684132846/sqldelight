/*
 * Tencent is pleased to support the open source community by making ovCompose available.
 * Copyright (C) 2025 THL A29 Limited, a Tencent company. All rights reserved.
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

package com.tencent.compose.sample.mainpage

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tencent.compose.db.MyDatabase
import com.tencent.compose.sample.XmlUtilTest
import com.tencent.compose.sample.KsoupTest
import com.tencent.compose.sample.backhandler.BackHandler
//import com.tencent.compose.sample.createTestDriver
import com.tencent.compose.sample.data.DisplayItem
import com.tencent.compose.sample.data.DisplaySection
import com.tencent.compose.sample.rememberLocalImage
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi


@Serializable
class Test1 {
    var a: String = ""
}

val encodeJsonFormat = Json { encodeDefaults = true }
inline fun <reified T> gsonToString(obj: T): String {
    obj?.let { t ->
        return encodeJsonFormat.encodeToString(t)
    }
    return ""
}

private fun appBarTitle(openedExample: DisplayItem?, skiaRender: Boolean = true): String {
    val title =
        if (skiaRender) "Tencent Video Compose - Skia" else "Tencent Video Compose - UIKit"

    val childTitle =
        if (skiaRender) "${openedExample?.title} - Skia" else "${openedExample?.title} - UIKit"

    return if (openedExample != null) childTitle else title
}

fun testJson(): String {
    return "${Test1().apply { a = "testac" }.let { gsonToString(it) }}"
}

fun testXmlUtil(): String {
    return try {
        val xmlTest = XmlUtilTest()
        val serializedPerson = xmlTest.testXmlSerialization()
        val deserializedPerson = xmlTest.testXmlDeserialization(serializedPerson)
        if (deserializedPerson != null) {
            "XML 序列化测试成功: $serializedPerson, 反序列化结果: ${deserializedPerson.name}, ${deserializedPerson.age}"
        } else {
            "XML 测试失败: 无法反序列化数据"
        }
    } catch (e: Exception) {
        "XML 测试失败: ${e.message}"
    }
}

//fun testDatabase(): String {
//    return try {
//        // 创建数据库驱动和实例
//        val driver = createTestDriver()
//        val database = MyDatabase(driver)
//        val personQueries = database.personQueries
//
//        // 测试数据库操作
//        // 1. 清空现有数据
//        personQueries.deleteAll()
//
//        // 2. 插入测试数据
//        personQueries.insertPerson(1, "测试用户", 25)
//
//        // 3. 查询数据
//        val persons = personQueries.selectAll().executeAsList()
//
//        // 4. 验证数据
//        if (persons.isNotEmpty() && persons[0].name == "测试用户") {
//            // 5. 更新数据
//            personQueries.updatePerson("更新用户", 30, 1)
//            val updatedPerson = personQueries.selectById(1).executeAsOneOrNull()
//
//            // 6. 删除数据
//            personQueries.deletePerson(1)
//            val remainingPersons = personQueries.selectAll().executeAsList()
//
//            if (remainingPersons.isEmpty() && updatedPerson?.name == "更新用户") {
//                "SQLDelight数据库测试通过: 增删改查功能正常"
//            } else {
//                "SQLDelight数据库测试失败: 数据操作结果不符合预期"
//            }
//        } else {
//            "SQLDelight数据库测试失败: 数据插入或查询失败"
//        }
//    } catch (e: Exception) {
//        "数据库测试失败: ${e.message}"
//    }
//}


fun testKsoup(): String {
    return try {
        val ksoupTest = KsoupTest()
        ksoupTest.runAllTests()
    } catch (e: Exception) {
        "Ksoup 测试失败: ${e.message}"
    }
}


@Composable
internal fun MainPage(skiaRender: Boolean = true) {
    val displayItems by remember { mutableStateOf(displaySections()) }
    var openedExample: DisplayItem? by remember { mutableStateOf(null) }
    val listState = rememberLazyListState()
    BackHandler(openedExample != null) {
        openedExample = null
    }
    Column {
        TopAppBar(
            navigationIcon = {
                if (openedExample == null) return@TopAppBar
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.clickable { openedExample = null }
                )
            },
            title = {
//                val title = testKsoup()
//                val title = testXmlUtil()
//                val title = testDatabase()
//                val title = testXmlUtil()
//                val title = testCoroutines()
                val title = testJson()
                Text(title)
            }
        )

        AnimatedContent(
            targetState = openedExample,
            transitionSpec = {
                val slideDirection =
                    if (targetState != null) AnimatedContentTransitionScope.SlideDirection.Start
                    else AnimatedContentTransitionScope.SlideDirection.End

                slideIntoContainer(
                    animationSpec = tween(250, easing = FastOutSlowInEasing),
                    towards = slideDirection
                ) + fadeIn(animationSpec = tween(250)) togetherWith
                        slideOutOfContainer(
                            animationSpec = tween(250, easing = FastOutSlowInEasing),
                            towards = slideDirection
                        ) + fadeOut(animationSpec = tween(250))
            }
        ) { target ->
            if (target == null) {
                LazyColumn(state = listState) {
                    items(displayItems) { displayItem ->
                        Section(displayItem) { clickItem ->
                            openedExample = clickItem
                        }
                    }

                    item {
                        Spacer(Modifier.fillMaxWidth().height(20.dp))
                    }
                }
            } else {
                Box(Modifier.fillMaxSize()) {
                    target.content()
                }
            }
        }
    }
}

@Composable
private fun Section(displaySection: DisplaySection, itemClick: (displayItem: DisplayItem) -> Unit) {
    Column {
        SectionTitle(displaySection.sectionTitle)
        val chunkedItems = displaySection.items.chunked(3)
        chunkedItems.forEach { rowItems ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                rowItems.forEach { BoxItem(it, itemClick) }

                repeat(3 - rowItems.size) {
                    Spacer(Modifier.weight(0.33f))
                }
            }
        }
    }
}

@Composable
private fun SectionTitle(title: String) {
    Row(
        Modifier.fillMaxWidth().height(34.dp).background(Color.Black.copy(alpha = 0.11f)),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(Modifier.fillMaxHeight().width(10.dp))
        Text(
            text = title,
            fontSize = 16.sp,
            color = Color.Black.copy(alpha = 0.7f),
            fontWeight = FontWeight.Medium
        )
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun RowScope.BoxItem(
    displayItem: DisplayItem,
    itemClick: (displayItem: DisplayItem) -> Unit
) {
    Column(
        Modifier.weight(0.33f)
            .height(86.dp)
            .border(0.3.dp, color = Color.LightGray).clickable {
                itemClick(displayItem)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            modifier = Modifier.width(34.dp).size(28.dp),
            bitmap = rememberLocalImage(displayItem.img),
            contentDescription = null
        )

        Spacer(Modifier.fillMaxWidth().height(10.dp))
        Text(text = displayItem.title, fontSize = 14.sp, color = Color.Black.copy(alpha = 0.7f))
    }
}