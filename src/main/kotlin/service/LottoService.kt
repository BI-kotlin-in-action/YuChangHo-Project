package service

import domain.Lotto

class LottoService {

    fun setManualCountAndAutoCount(lotto: Lotto, totalCount: Int) {
        lotto.manualLottoCount = readLine()!!.toInt()
        lotto.autoLottoCount = totalCount - lotto.manualLottoCount
    }

    fun setManualLottoNum(lotto: Lotto) {
        val manualLottoNum = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
        manualLottoNum.sort()
        lotto.lottoNum.add(manualLottoNum)
    }

    fun makeRandomNum(): IntArray {
        val autoLottoNum = IntArray(6)
        val range = (1..45)
        var i = 0
        while (i < 6) {
            val randomNumber = range.random()

            if (randomNumber !in autoLottoNum) {
                autoLottoNum[i] = randomNumber
                i++
            }
        }

        autoLottoNum.sort()
        return autoLottoNum
    }

    fun showLottoNum(lotto: Lotto) {
        for (num in lotto.lottoNum) {
            println(num.contentToString())
        }
    }

    fun getResult(lotto: Lotto): Lotto {
        lotto.rank.fill(0)
        lotto.prize = 0
        val winNum = lotto.winNum

        for (nums in lotto.lottoNum) {
            var count = 0

            for (i in nums.indices) {
                if (nums[i] in winNum) {
                    count++
                }
            }
            if (count >= 3) {
                lotto.rank[6 - count]++
            }
        }
        return lotto
    }
}
