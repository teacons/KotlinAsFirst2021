package lesson7.task1

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import java.io.File
import kotlin.random.Random

@Tag("AnotherTest")
internal class AnotherTests {

    @Test
    fun markdownToHtmlSimple() {
        assertAll(
//      Обычный текст с разными языками
            {
                markdownToHtmlSimple("input/lesson7_another_tests_normal_input.md", "temp.html")
                val expected = File("expected/lesson7_another_tests_normal_input.html").bufferedReader().readText()
                assertEquals(
                    expected,
                    File("temp.html").readText()
                )
                File("temp.html").delete()
            },

//      Текст с EOF
            {
                markdownToHtmlSimple("input/lesson7_another_tests_eof_input.md", "temp.html")
                val expectedEOF =
                    File("input/lesson7_another_tests_eof_output.md").readText().trimIndent()

                assertEquals(
                    expectedEOF,
                    File("temp.html").readText()
                )
                File("temp.html").delete()
            },

//      Пустой файл
            {
                File("empty").createNewFile()
                markdownToHtmlSimple("empty", "temp.html")
                val expectedEmpty =
                    """
                    <html>
                    <body>
                    </body>
                    </html>
                    """.trimIndent()

                assertEquals(
                    expectedEmpty,
                    File("temp.html").readText()
                )
                File("empty").delete()
                File("temp.html").delete()
            },

//          Fuzzing
            {
                val inputFile = File("temp_fuz")
                inputFile.createNewFile()
                val bf = inputFile.bufferedWriter()
                bf.write(generateRandomString(100000))
                bf.close()


                assertDoesNotThrow {
                    markdownToHtmlSimple("temp_fuz", "temp.html")
                }
                inputFile.delete()
                File("temp.html").delete()
            }
        )
    }

    @Test
    fun deleteMarked() {
        assertAll(
//      Пустой файл
            {
                File("empty").createNewFile()
                deleteMarked("empty", "temp.txt")
                val expectedEmpty = ""

                assertEquals(
                    expectedEmpty,
                    File("temp.txt").readText()
                )
                File("empty").delete()
                File("temp.txt").delete()
            },

//      Обычный текстовый файл
            {
                deleteMarked("input/lesson7_another_tests_to_delete.txt", "temp.txt")
                val expected = File("expected/lesson7_another_tests_to_delete.txt").bufferedReader().readText()

                assertEquals(
                    expected,
                    File("temp.txt").readText()
                )
            })
    }

    private fun generateRandomString(length: Int): String {
        val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9') + listOf('*', '~')
        val sb = StringBuilder()
        repeat(length) {
            val index = Random.nextInt(0, charPool.size)
            sb.append(charPool[index])
        }
        return sb.toString()
    }

    companion object {

        @AfterAll
        @JvmStatic
        fun tryDeleteTempFiles() {
            File("temp.txt").delete()
            File("temp.html").delete()
            File("temp_fuz.md").delete()
            File("empty").delete()
        }
    }


}