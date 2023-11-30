package lottoWebApp.controller

import lottoWebApp.domain.LottoResult
import lottoWebApp.service.LottoResultService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LottoResultController(val lottoResultService: LottoResultService) {

    @PostMapping("/lotto/result/save")
    fun saveLottoResultController(@RequestBody lottoResult: LottoResult): LottoResult {
        lottoResultService.saveResult(lottoResult.name)
        return lottoResultService.getResult(lottoResult.name)
    }

    @GetMapping("/lotto/result/{name}")
    fun getLottoResultController(@PathVariable name: String): LottoResult {
        return lottoResultService.getResult(name)
    }
}