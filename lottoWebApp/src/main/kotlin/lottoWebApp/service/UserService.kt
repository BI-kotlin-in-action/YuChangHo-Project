package lottoWebApp.service

import lottoWebApp.domain.User
import lottoWebApp.repository.UserLottoRepository
import lottoWebApp.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun findAll(): List<User> {
        return userRepository.findAll();
    }

    fun findUserByName(name: String): List<User> {
        return userRepository.findByName(name)
    }

    fun saveUser(user: User) {
        userRepository.save(user)
    }

    fun chargeMoney(name: String, money: Int) {
        val user = findUserByName(name)[0]
        user.money = money
        saveUser(user)
    }

    fun buyManualOrAuto(name: String, manualCount: Int, autoCount: Int) {
        val user = findUserByName(name)[0]
        user.manualLottoCount = manualCount
        user.autoLottoCount = autoCount
        saveUser(user)
    }

    fun withdrawMoney(name: String, money: Int) {
        val user = findUserByName(name)[0]
        user.money -= money
        saveUser(user)
    }
}