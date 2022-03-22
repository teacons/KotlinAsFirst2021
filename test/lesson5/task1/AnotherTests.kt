package lesson5.task1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.random.Random

internal class AnotherTests {

    @Test
    fun findSumOfTwo() {
//      Проверка ожидаемого входа
        assertEquals(
            2 to 4,
            findSumOfTwo(
                listOf(1, 22, 44, 33, 20),
                64
            )
        )

        assertEquals(
            0 to 4,
            findSumOfTwo(
                listOf(1, 22, 44, 33, 20),
                21
            )
        )

        assertEquals(
            2 to 3,
            findSumOfTwo(
                listOf(1, 22, 44, 33, 20),
                77
            )
        )

        assertEquals(
            0 to 4,
            findSumOfTwo(
                listOf(1, 22, 44, 33, 20),
                21
            )
        )

        repeat(15) {
            val generated = generateRandomListAndNumberAndExpected()
            println("Generated №${it + 1}:")
            println("list: ${generated.first}")
            println("number: ${generated.second}")
            println("expected: ${generated.third}")
            assertEquals(
                generated.third,
                findSumOfTwo(
                    generated.first,
                    generated.second
                )
            )
        }


//      Проверка составления пар
        assertTrue(
            findSumOfTwo(
                listOf(1, 1, 1),
                2
            ) in listOf(0 to 1, 0 to 2, 1 to 2)
        )

        assertTrue(
            findSumOfTwo(
                listOf(0, 0, 0),
                0
            ) in listOf(0 to 1, 0 to 2, 1 to 2)
        )

        assertTrue(
            findSumOfTwo(
                listOf(Int.MAX_VALUE / 2, Int.MAX_VALUE / 2, Int.MAX_VALUE / 2),
                (Int.MAX_VALUE / 2) * 2
            ) in listOf(0 to 1, 0 to 2, 1 to 2)
        )

//      Проверка отсутствующего результата
        assertEquals(
            -1 to -1,
            findSumOfTwo(
                listOf(1, 22, 44, 33, 20),
                78
            )
        )

        assertEquals(
            -1 to -1,
            findSumOfTwo(
                listOf(0, 1, 2),
                10
            )
        )

//      Проверка пустого списка
        assertEquals(
            -1 to -1,
            findSumOfTwo(
                emptyList(),
                10
            )
        )

//      Проверка дублирования индексов
        assertEquals(
            -1 to -1,
            findSumOfTwo(
                listOf(1, 2, 3),
                2
            )
        )

        assertEquals(
            -1 to -1,
            findSumOfTwo(
                listOf(1000, 2000, 3000),
                2000
            )
        )

        assertEquals(
            0 to 1,
            findSumOfTwo(
                listOf(5, 15, 20),
                20
            )
        )

//      Проверка списка с отрицательными числами (поведение не задано в задании, поэтому ждем Exception)
        val inputNegativeList = List(Random.nextInt(0, 25)) { Random.nextInt(Int.MIN_VALUE, 0) }
        assertThrows(Exception::class.java) { findSumOfTwo(inputNegativeList, 25) }

    }

    private fun generateRandomListAndNumberAndExpected(): Triple<List<Int>, Int, Pair<Int, Int>> {
        val list = List(Random.nextInt(10, 25)) { Random.nextInt(0, Int.MAX_VALUE / 2) }

        val ind1 = Random.nextInt(0, list.size - 1)
        var ind2 = Random.nextInt(0, list.size - 1)

        while (ind1 == ind2)
            ind2 = Random.nextInt(0, list.size - 1)

        val number = list[ind1] + list[ind2]

        val expected = if (ind1 > ind2) ind2 to ind1 else ind1 to ind2

        return Triple(list, number, expected)
    }

    @Test
    fun hasAnagrams() {
//      Проверка работы на нормальных данных
        assertTrue(
            hasAnagrams(
                listOf("колба", "бокал", "инвалид")
            )
        )

        assertTrue(
            hasAnagrams(
                listOf("клоун", "уклон", "апельсин", "яблоко")
            )
        )

        assertTrue(
            hasAnagrams(
                listOf("приказ", "каприз", "чучело", "матрос", "больница")
            )
        )

        assertFalse(
            hasAnagrams(
                listOf("чучело", "матрос", "больница")
            )
        )

//      Проверка работы с разным регистром
        assertTrue(
            hasAnagrams(
                listOf("КоЛбА", "БОкал", "инВАЛид")
            )
        )

        assertTrue(
            hasAnagrams(
                listOf("клОун", "Уклон", "апелЬСИН", "яблОКО")
            )
        )

        assertTrue(
            hasAnagrams(
                listOf("Приказ", "Каприз", "Чучело", "Матрос", "Больница")
            )
        )

//      Проверка работы с символами
        assertTrue(
            hasAnagrams(
                listOf("123!@#", "#@!321", "!@#$%^&*()")
            )
        )

        assertTrue(
            hasAnagrams(
                listOf("1", "1", "!")
            )
        )

//      Проверка недостаточной размерности списка
        assertFalse(
            hasAnagrams(
                listOf("инвалид")
            )
        )

        assertFalse(
            hasAnagrams(
                emptyList()
            )
        )
    }
}