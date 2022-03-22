package lesson7.task1

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class AnotherTests {

    @Test
    fun markdownToHtmlSimple() {
//      Обычный текст с разными языками
        markdownToHtmlSimple("input/lesson7_another_tests_normal_input.md", "temp.html")
        val expected =
            """
                <html>
                <body>
                <p>
                Принимая во <s>внимание показатели успешности</s>, внедрение современных методик выявляет срочную потребность существующих финансовых и административных условий.
                </p>
                <p>
                Предварительные выводы неутешительны: начало <b><i><s>повседневной</s></i></b> работы по формированию позиции создаёт необходимость включения в производственный план целого ряда внеочередных мероприятий с учётом комплекса укрепления моральных ценностей.
                Ра<i>знообразный и <b>богатый опыт гово<s>рит нам, что новая модель орган</b>изационной деятельн</s>ости является качестве</i>нно новой ступенью форм воздействия.
                </p>
                <p>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
                eiusmod tempor incididunt ut labore et dolore magna aliqua.
                Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                </p>
                <p>
                Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
                </p>
                <p>
                Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                </p>
                </body>
                </html>
            """.trimIndent()
        assertEquals(
            expected,
            File("temp.html").readText()
        )
        File("temp.html").delete()

//      Текст с EOF
        markdownToHtmlSimple("input/lesson7_another_tests_eof_input.md", "temp.html")
        val expectedEOF =
            File("input/lesson7_another_tests_eof_output.md").readText().trimIndent()

        assertEquals(
            expectedEOF,
            File("temp.html").readText()
        )
        File("temp.html").delete()

//      Пустой файл
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
    }

    @Test
    fun deleteMarked() {
//      Пустой файл
        File("empty").createNewFile()
        deleteMarked("empty", "temp.txt")
        val expectedEmpty = ""

        assertEquals(
            expectedEmpty,
            File("temp.txt").readText()
        )
        File("empty").delete()
        File("temp.txt").delete()

//      Обычный текстовый файл
        deleteMarked("input/lesson7_another_tests_to_delete.txt", "temp.txt")
        val expected = """
            Lorem ipsum dolor sit amet,_ consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
            Arcu cursus euismod quis viverra.
            Ac ut consequat semper viv_erra nam libero justo.


            В своём стремлении повысить качество жизни, они забывают, что экономи_ческая повестка сегодняшнего дня представляет собой интересный эксперимент проверки стандартных подходов.
            Задача организации, в особенности же базовый вектор развития создаёт необходимость включения в производственный план целого ряда внеочередных мероприятий с учётом комплекса поэтапного и последовательного развития общества.
        """.trimIndent()

        assertEquals(
            expected,
            File("temp.txt").readText()
        )
    }

    companion object {

        @AfterAll
        @JvmStatic
        fun tryDeleteTempFiles() {
            File("temp.txt").delete()
            File("temp.html").delete()
            File("empty").delete()
        }
    }


}