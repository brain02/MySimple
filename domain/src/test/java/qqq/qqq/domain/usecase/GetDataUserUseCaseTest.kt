package qqq.qqq.domain.usecase

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import qqq.qqq.domain.models.UserData
import qqq.qqq.domain.repository.UserRepository


class TestRepository : UserRepository {
    override fun getUser(): UserData {
        return UserData(firstName = "Ivan", lastName = "Kovalenko")
    }

    override fun saveUser(userData: UserData): Boolean {
        TODO("Not yet implemented")
    }

}

class GetDataUserUseCaseTest {

    @Test
    fun test() {
        val testRepository = TestRepository()
        val useCase = GetDataUserUseCase(userRepository = testRepository)
        val result = useCase.execute()
        val expected = UserData(firstName = "Ivan", lastName = "Kovalenko")
        assertEquals(expected, result)
    }
}