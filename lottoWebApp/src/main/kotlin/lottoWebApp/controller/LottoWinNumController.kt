package lottoWebApp.controller

import lottoWebApp.domain.LottoWinNum
import lottoWebApp.service.LottoWinNumService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LottoWinNumController(val lottoWinNumService: LottoWinNumService) {
    @GetMapping("/lotto-Win-Num")
    fun setLottoWinNum(): LottoWinNum {
        lottoWinNumService.saveLottoWinNum()

        return lottoWinNumService.getLottoWinNum()
    }
}