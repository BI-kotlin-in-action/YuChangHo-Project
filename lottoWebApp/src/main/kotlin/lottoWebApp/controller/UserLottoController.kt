package lottoWebApp.controller

import lottoWebApp.domain.User
import lottoWebApp.domain.UserLotto
import lottoWebApp.service.UserLottoService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
public class UserLottoController(val userLottoService:UserLottoService) {
    @PostMapping("/manual-lotto/add")
    fun manualLottoNumController(@RequestBody userLotto:UserLotto): List<UserLotto> {
        userLottoService.saveManualLottoNum(userLotto.name, userLotto.lottoNum)

        return userLottoService.findByName(userLotto.name)
    }

    @PostMapping("/auto-lotto/add")
    fun autoLottoController(@RequestBody user: User): List<UserLotto> {
        userLottoService.saveAutoLottoNum(user.name, user.autoLottoCount)

        return userLottoService.findByName(user.name)
    }
}
