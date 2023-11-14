package service

import domain.Lotto
import domain.LottoResult
import domain.PrizeByRank
import domain.User

class LottoService {
    companion object {
        const val MAX_RANK: Int = 4
        const val LOTTO_START_NUM: Int = 1
        const val LOTTO_END_NUM: Int = 45
        const val LOTTO_SIZE: Int = 6

        @JvmStatic
        val NUMBERS = (LOTTO_START_NUM..LOTTO_END_NUM).toList()
    }

    fun setManualCountAndAutoCount(user: User, totalCount: Int) {
        user.manualLottoCount = readln().toInt()
        user.autoLottoCount = totalCount - user.manualLottoCount
    }

    fun setManualLottoNum(user: User) {
        val manualLottoNum = Lotto(readln().split(" ").map { it.toInt() })
        user.lottoNum.add(manualLottoNum)
    }

    fun makeRandomNum(): List<Int> {
//        val autoLottoNum: TreeSet<Int> = sortedSetOf()
//
//        val range = (LOTTO_START_NUM..LOTTO_END_NUM)
//        var i = 0
//        while (i < LOTTO_SIZE) {
//            val randomNumber = range.random()
//
//            if (randomNumber !in autoLottoNum) {
//                autoLottoNum.add(randomNumber)
//                i++
//            }
//        }
//
//        return autoLottoNum

        val shuffledNums = NUMBERS.shuffled()
        return shuffledNums.subList(0, LOTTO_SIZE)
    }

    fun getResult(lottoResult: LottoResult, winLotto: Lotto, user: User): LottoResult {
        for (nums in user.lottoNum) {
            val rank = PrizeByRank.getRank(nums, winLotto)

            if (rank != PrizeByRank.LOSE) {
                lottoResult.rank[rank] = lottoResult.rank.getOrDefault(rank, 0) + 1
            }
        }

        return lottoResult
    }
}
