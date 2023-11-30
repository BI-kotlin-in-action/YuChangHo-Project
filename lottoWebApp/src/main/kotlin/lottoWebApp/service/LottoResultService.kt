package lottoWebApp.service

import lottoWebApp.domain.LottoResult
import lottoWebApp.domain.PrizeByRank
import lottoWebApp.domain.UserLotto
import lottoWebApp.repository.LottoResultRepository
import lottoWebApp.repository.LottoWinNumRepository
import lottoWebApp.repository.UserLottoRepository
import org.springframework.stereotype.Service

@Service
class LottoResultService(private val lottoResultRepository: LottoResultRepository,
                         private val userLottoRepository: UserLottoRepository,
                         private val lottoWinNumRepository: LottoWinNumRepository) {
    companion object {
        const val MAX_RANK: Int = 4
    }
    fun saveResult(name: String) {
        val lottoList = userLottoRepository.findByName(name)
        val lottoResult = LottoResult(name = name)

        val winNum = lottoWinNumRepository.findById(1).orElse(null)?.winNum?.split(", ")?.toSet()!!
        for (nums in lottoList) {
            var count = nums.lottoNum.split("", "").toSet().intersect(winNum).size

            if (count >= (MAX_RANK - 1)) {
                val rank = PrizeByRank.getRank(count)
                lottoResult.addRankCount(rank)
            }
        }

        PrizeByRank.values().forEach { prizeByRank ->
            if (prizeByRank.rank != 5) {
                lottoResult.addPrize(prizeByRank)
            }
        }

        lottoResultRepository.save(lottoResult)
    }

    fun getResult(name: String): LottoResult {
        return lottoResultRepository.findById(name).get()
    }
}