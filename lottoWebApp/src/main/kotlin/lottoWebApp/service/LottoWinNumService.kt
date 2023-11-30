package lottoWebApp.service;

import lottoWebApp.domain.LottoWinNum
import lottoWebApp.repository.LottoWinNumRepository
import org.springframework.stereotype.Service

@Service
public class LottoWinNumService (private val lottoWinNumRepository: LottoWinNumRepository){
    fun saveLottoWinNum(){
        val winNum = UserLottoService.makeRandomNum().joinToString(", ")
        val lottoWinNum = LottoWinNum(winNum = winNum)

        lottoWinNumRepository.save(lottoWinNum)
    }

    fun getLottoWinNum() :LottoWinNum{
        return lottoWinNumRepository.findById(1).get()
    }
}
