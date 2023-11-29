package lottoWebApp.controller

import lottoWebApp.domain.User
import lottoWebApp.domain.UserLotto
import lottoWebApp.service.UserLottoService
import lottoWebApp.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LottoController(val userService: UserService, val userLottoService: UserLottoService) {

    @GetMapping("/all-user")
    fun findAllUserController(): List<User> {
        return userService.findAll()
    }

    @PostMapping("/user/new")
    fun addUserController(@RequestBody user: User): List<User> {
        userService.saveUser(user)

        return userService.findUserByName(user.name)
    }

    @PostMapping("/money/add")
    fun chargeMoneyController(@RequestBody user: User): List<User> {
        userService.chargeMoney(user.name, user.money)

        return userService.findUserByName(user.name)
    }

    @PostMapping("/lotto-type")
    fun buyManualOrAutoController(@RequestBody user: User): List<User> {
        userService.buyManualOrAuto(user.name, user.manualLottoCount, user.autoLottoCount)

        return userService.findUserByName(user.name)
    }

    @PostMapping("/manual-lotto/add")
    fun manualLottoNumController(@RequestBody userLotto: UserLotto): List<UserLotto> {
        userLottoService.saveManualLottoNum(userLotto.name, userLotto.lottoNum)

        return userLottoService.findByName(userLotto.name)
    }

    @PostMapping("/auto-lotto/add")
    fun autoLottoController(@RequestBody user: User): List<UserLotto> {
        userLottoService.saveAutoLottoNum(user.name, user.autoLottoCount)

        return userLottoService.findByName(user.name)
    }

    @PostMapping("/money/update")
    fun withdrawMoneyController(@RequestBody user: User): List<User> {
        userService.withdrawMoney(user.name, user.money)

        return userService.findUserByName(user.name)
    }


    @GetMapping("/lotto/win")
    fun checkLottoWinController() {

    }

}
