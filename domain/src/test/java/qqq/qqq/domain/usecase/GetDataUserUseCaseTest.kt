package qqq.qqq.domain.usecase

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import qqq.qqq.domain.models.UserData
import qqq.qqq.domain.repository.UserRepository


//class TestRepository : UserRepository {
//    override fun getUser(): UserData {
//        return UserData(firstName = "Ivan", lastName = "Kovalenko")
//    }
//
//    override fun saveUser(userData: UserData): Boolean {
//        TODO("Not yet implemented")
//    }
//
//}

class GetDataUserUseCaseTest {

    val userRepository = mock<UserRepository>()

    @Test
    fun test() {
        val testUserData = UserData(firstName = "Ivan", lastName = "Kovalenko")
        Mockito.`when`(userRepository.getUser()).thenReturn(testUserData)

        val useCase = GetDataUserUseCase(userRepository = userRepository)
        val actual = useCase.execute()
        val expected = UserData(firstName = "Ivan", lastName = "Kovalenko")
        assertEquals(expected, actual)
    }
}