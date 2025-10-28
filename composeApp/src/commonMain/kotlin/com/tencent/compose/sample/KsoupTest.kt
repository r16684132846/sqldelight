package com.tencent.compose.sample

import com.mohamedrejeb.ksoup.entities.KsoupEntities
import com.mohamedrejeb.ksoup.html.parser.KsoupHtmlHandler
import com.mohamedrejeb.ksoup.html.parser.KsoupHtmlParser

class KsoupTest {
    fun testKsoupHtmlParsing(): String {
        return try {
            // 测试基本的 HTML 解析功能
            val html = """
                <html>
                    <head><title>Test Title</title></head>
                    <body>
                        <h1>Header 1</h1>
                        <p>Paragraph text</p>
                    </body>
                </html>
            """.trimIndent()

            // 使用 Ksoup 解析 HTML
            var title = ""
            var h1Text = ""
            var pText = ""

            // 使用局部变量跟踪状态
            var isInsideTitle = false
            var isInsideH1 = false
            var isInsideP = false

            // 解析标题
            val titleHandler = KsoupHtmlHandler
                .Builder()
                .onOpenTag { name, _, _ ->
                    if (name == "title") {
                        isInsideTitle = true
                    }
                }
                .onText { text ->
                    if (isInsideTitle) {
                        title = text
                    }
                }
                .onCloseTag { name, _ ->
                    if (name == "title") {
                        isInsideTitle = false
                    }
                }
                .build()

            val titleParser = KsoupHtmlParser(titleHandler)
            titleParser.write(html)
            titleParser.end()

            // 解析 h1
            val h1Handler = KsoupHtmlHandler
                .Builder()
                .onOpenTag { name, _, _ ->
                    if (name == "h1") {
                        isInsideH1 = true
                    }
                }
                .onText { text ->
                    if (isInsideH1) {
                        h1Text = text
                    }
                }
                .onCloseTag { name, _ ->
                    if (name == "h1") {
                        isInsideH1 = false
                    }
                }
                .build()

            val h1Parser = KsoupHtmlParser(h1Handler)
            h1Parser.write(html)
            h1Parser.end()

            // 解析 p
            val pHandler = KsoupHtmlHandler
                .Builder()
                .onOpenTag { name, _, _ ->
                    if (name == "p") {
                        isInsideP = true
                    }
                }
                .onText { text ->
                    if (isInsideP) {
                        pText = text
                    }
                }
                .onCloseTag { name, _ ->
                    if (name == "p") {
                        isInsideP = false
                    }
                }
                .build()

            val pParser = KsoupHtmlParser(pHandler)
            pParser.write(html)
            pParser.end()

            "Ksoup 解析成功: 标题='$title', H1='$h1Text', P='$pText'"
        } catch (e: Exception) {
            "Ksoup HTML 解析失败: ${e.message}"
        }
    }

    fun testKsoupElementSelection(): String {
        return try {
            // 测试元素选择功能
            val html = """
                <div class="content">
                    <p class="intro">Introduction text</p>
                    <p class="main">Main content</p>
                    <a href="https://example.com">Example Link</a>
                </div>
            """.trimIndent()

            var introText = ""
            var linkHref = ""

            // 使用局部变量跟踪状态
            var isInsideIntro = false

            // 解析 intro 文本
            val introHandler = KsoupHtmlHandler
                .Builder()
                .onOpenTag { name, attributes, _ ->
                    if (name == "p" && attributes["class"] == "intro") {
                        isInsideIntro = true
                    }
                }
                .onText { text ->
                    if (isInsideIntro) {
                        introText = text
                    }
                }
                .onCloseTag { name, _ ->
                    if (name == "p") {
                        isInsideIntro = false
                    }
                }
                .build()

            val introParser = KsoupHtmlParser(introHandler)
            introParser.write(html)
            introParser.end()

            // 解析链接
            val linkHandler = KsoupHtmlHandler
                .Builder()
                .onOpenTag { name, attributes, _ ->
                    if (name == "a") {
                        linkHref = attributes["href"] ?: ""
                    }
                }
                .build()

            val linkParser = KsoupHtmlParser(linkHandler)
            linkParser.write(html)
            linkParser.end()

            "Ksoup 元素选择成功: Intro='$introText', Link='$linkHref'"
        } catch (e: Exception) {
            "Ksoup 元素选择失败: ${e.message}"
        }
    }

    fun testKsoupHtmlCleaning(): String {
        return try {
            // 测试 HTML 实体编码/解码功能
            val text = "This is a test & example <script>alert('test')</script>"
            val encodedText = KsoupEntities.encodeHtml(text)
            val decodedText = KsoupEntities.decodeHtml(encodedText)

            "Ksoup HTML 编码解码成功: 原文='$text', 编码后='$encodedText', 解码后='$decodedText'"
        } catch (e: Exception) {
            "Ksoup HTML 编码解码失败: ${e.message}"
        }
    }

    // 测试所有功能
    fun runAllTests(): String {
        val results = mutableListOf<String>()

        results.add("=== KsoupTest 测试结果 ===")
        results.add(testKsoupHtmlParsing())
        results.add(testKsoupElementSelection())
        results.add(testKsoupHtmlCleaning())

        return results.joinToString("\n")
    }
}
