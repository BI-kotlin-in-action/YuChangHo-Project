package service

import domain.Lotto
import domain.LottoResult
import domain.User
import java.util.*
import java.util.Collections.shuffle

class LottoService {
    companion object {
        const val MAX_RANK: Int = 4
        const val LOTTO_START_NUM: Int = 1
        const val LOTTO_END_NUM: Int = 45
        const val LOTTO_SIZE: Int = 6
    }

    fun setManualCountAndAutoCount(user: User, totalCount: Int) {
        user.manualLottoCount = readLine()!!.toInt()
        user.autoLottoCount = totalCount - user.manualLottoCount
    }

    fun setManualLottoNum(user: User) {
        val manualLottoNum = readLine()!!.split(" ").map { it.toInt() }.toSortedSet()
        user.lottoNum.add(manualLottoNum)
    }

    fun makeRandomNum(): SortedSet<Int> {
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

        val numbers = (LOTTO_START_NUM..LOTTO_END_NUM).toList()
        shuffle(numbers)

        return numbers.subList(0, 6).toSortedSet()
    }

    fun showLottoNum(user: User) {
        for (num in user.lottoNum) {
            println(num)
        }
    }

    fun getResult(lottoResult: LottoResult, lotto: Lotto, user: User): LottoResult {
        lottoResult.rank.fill(0)
        lottoResult.prize = 0
        val winNum = lotto.winNum

        for (nums in user.lottoNum) {
            var count = nums.intersect(winNum).size
            if (count >= (MAX_RANK - 1)) {
                lottoResult.rank[LOTTO_SIZE - count]++
            }
        }
        return lottoResult
    }
}
