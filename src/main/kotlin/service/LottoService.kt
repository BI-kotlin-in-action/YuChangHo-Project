package service

import domain.Lotto
import java.util.*

class LottoService {
    companion object {
        const val MAX_RANK: Int = 4
        const val LOTTO_START_NUM: Int = 1
        const val LOTTO_END_NUM: Int = 45
        const val LOTTO_SIZE: Int = 6
    }

    fun setManualCountAndAutoCount(lotto: Lotto, totalCount: Int) {
        lotto.manualLottoCount = readLine()!!.toInt()
        lotto.autoLottoCount = totalCount - lotto.manualLottoCount
    }

    fun setManualLottoNum(lotto: Lotto) {
        val manualLottoNum = readLine()!!.split(" ").map { it.toInt() }.toSortedSet()
        lotto.lottoNum.add(manualLottoNum)
    }

    fun makeRandomNum(): SortedSet<Int> {
        val autoLottoNum: TreeSet<Int> = sortedSetOf()

        val range = (LOTTO_START_NUM..LOTTO_END_NUM)
        var i = 0
        while (i < LOTTO_SIZE) {
            val randomNumber = range.random()

            if (randomNumber !in autoLottoNum) {
                autoLottoNum.add(randomNumber)
                i++
            }
        }

        return autoLottoNum
    }

    fun showLottoNum(lotto: Lotto) {
        for (num in lotto.lottoNum) {
            println(num)
        }
    }

    fun getResult(lotto: Lotto): Lotto {
        lotto.rank.fill(0)
        lotto.prize = 0
        val winNum = lotto.winNum

        for (nums in lotto.lottoNum) {
            var count = nums.intersect(winNum).size
            if (count >= (MAX_RANK - 1)) {
                lotto.rank[LOTTO_SIZE - count]++
            }
        }
        return lotto
    }
}
