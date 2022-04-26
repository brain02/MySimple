package qqq.qqq.domain.usecase

import org.junit.jupiter.api.Test

//Здесь пишу консольный код
class RunSimple {

    @Test
    fun run() {
                println(11%2) // [5, 5, 5, 4]
    }

    @Test
    fun main() {
        val words = "The quick brown fox jumps over the lazy dog".split(" ")
        //convert the List to a Sequence
        val wordsSequence = words.asSequence()

        val lengthsSequence = wordsSequence.filter { println("filter: $it"); it.length > 3 }
            .map { println("length: ${it.length}"); it.length }
            .take(4)

        println("Lengths of first 4 words longer than 3 chars")
        // terminal operation: obtaining the result as a List
        println(lengthsSequence.toList()) // [5, 5, 5, 4]
    }
}
