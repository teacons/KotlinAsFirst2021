package lesson6.task1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class AnotherTests {

    @Test
    fun dateStrToDigit() {
//      Проверка обычных входных данных
        assertEquals(
            "01.01.2022",
            dateStrToDigit("1 января 2022")
        )

        assertEquals(
            "13.02.2007",
            dateStrToDigit("13 февраля 2007")
        )

        assertEquals(
            "29.02.2020",
            dateStrToDigit("29 февраля 2020")
        )

//      Проверка неправильных календарных дней
        assertEquals(
            "",
            dateStrToDigit("29.02.2022")
        )

        assertEquals(
            "",
            dateStrToDigit("31 апреля 2022")
        )

        assertEquals(
            "",
            dateStrToDigit("311 небывабря 202225")
        )

        assertEquals(
            "",
            dateStrToDigit("311 ноября 202225")
        )

        assertEquals(
            "",
            dateStrToDigit("1 января 0")
        )

//      Проверка неправильного ввода даты
        assertEquals(
            "",
            dateStrToDigit("random text")
        )

        assertEquals(
            "",
            dateStrToDigit("random text org")
        )

        assertEquals(
            "",
            dateStrToDigit("01 января 2021")
        )

    }

    @Test
    fun dateDigitToStr() {
//      Проверка обычных входных данных
        assertEquals(
            "1 января 2022",
            dateDigitToStr("01.01.2022")
        )

        assertEquals(
            "13 февраля 2007",
            dateDigitToStr("13.02.2007")
        )

        assertEquals(
            "29 февраля 2020",
            dateDigitToStr("29.02.2020")
        )

//      Проверка неправильных календарных дней
        assertEquals(
            "",
            dateDigitToStr("29.02.2022")
        )

        assertEquals(
            "",
            dateDigitToStr("31.04.2022")
        )

        assertEquals(
            "",
            dateDigitToStr("311.113.202225")
        )

        assertEquals(
            "",
            dateDigitToStr("311.11.202225")
        )

        assertEquals(
            "",
            dateDigitToStr("01.01.0")
        )

//      Проверка неправильного ввода даты
        assertEquals(
            "",
            dateDigitToStr("random text")
        )

        assertEquals(
            "",
            dateDigitToStr("random.text.org")
        )

        assertEquals(
            "",
            dateDigitToStr("1.1.2021")
        )
    }
}