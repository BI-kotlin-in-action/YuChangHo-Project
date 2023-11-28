package lottoWebApp.controller

import lottoWebApp.domain.User
import lottoWebApp.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LottoController(val userService: UserService) {

    @GetMapping("/all-user")
    fun findAllUser(): List<User> {
        return userService.findAll()
    }

    @PostMapping("/user")
    fun addUser(@RequestBody user: User): List<User> {
        userService.saveUser(user)

        return userService.findUserByName(user.name)
    }

    @PostMapping("/money")
    fun chargeMoney(@RequestBody user: User): List<User> {
        userService.chargeMoney(user.name, user.money)

        return userService.findUserByName(user.name)
    }

}
