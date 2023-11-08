package service

import domain.Lotto
import java.util.*

class LottoService {

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

        val range = (1..45)
        var i = 0
        while (i < 6) {
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
            if (count >= 3) {
                lotto.rank[6 - count]++
            }
        }
        return lotto
    }
}
