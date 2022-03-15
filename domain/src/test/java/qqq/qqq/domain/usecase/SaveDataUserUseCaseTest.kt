package qqq.qqq.domain.usecase

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import qqq.qqq.domain.models.UserData
import qqq.qqq.domain.repository.UserRepository

class SaveDataUserUseCaseTest {

    val userRepository = mock<UserRepository>()


    @AfterEach
    fun tearDown() {
        Mockito.reset(userRepository)
    }

    @Test
    fun test() {
        val testUserData = UserData(firstName = "Ivan", lastName = "Kovalenko")
        Mockito.`when`(userRepository.getUser()).thenReturn(testUserData)

        val useCase = SaveDataUserUseCase(userRepository = userRepository)
        val testParams = UserData(firstName = "Ivan", lastName = "Kovalenko")
        val actual = useCase.execute(userData = testParams)
        val expected = true

        Assertions.assertEquals(expected, actual)
        Mockito.verify(userRepository, Mockito.never()).saveUser(userData = any())
    }

    @Test
    fun `should return true if save was successful`() {
        val testUserData = UserData(firstName = "Ivan", lastName = "Kovalenko")
        Mockito.`when`(userRepository.getUser()).thenReturn(testUserData)

        val expected = false
        val testParams = UserData(firstName = "Anton", lastName = "Kovalenko")
        Mockito.`when`(userRepository.saveUser(userData = testUserData)).thenReturn(expected)

        val useCase = SaveDataUserUseCase(userRepository = userRepository)
        val actual = useCase.execute(userData = testParams)

        Assertions.assertEquals(expected, actual)
        Mockito.verify(userRepository, Mockito.times(1)).saveUser(userData = testParams)
    }


}