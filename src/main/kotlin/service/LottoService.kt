package service

import domain.Lotto
import domain.LottoResult
import domain.User

class LottoService {
    companion object {
        const val MAX_RANK: Int = 4
        const val LOTTO_START_NUM: Int = 1
        const val LOTTO_END_NUM: Int = 45
        const val LOTTO_SIZE: Int = 6
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

    fun makeRandomNum(): Lotto {
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
        return Lotto(shuffledNums.subList(0, LOTTO_SIZE))
    }

    fun showLottoNum(user: User) {
        println(user.lottoNum.joinToString("\n"))
    }

    fun getResult(lottoResult: LottoResult, lotto: Lotto, user: User): LottoResult {
        lottoResult.rank.fill(0)
        lottoResult.prize = 0
        val winNum = lotto.num

        for (nums in user.lottoNum) {
            var count = nums.num.intersect(winNum).size
            if (count >= (MAX_RANK - 1)) {
                lottoResult.rank[LOTTO_SIZE - count]++
            }
        }
        return lottoResult
    }
}
