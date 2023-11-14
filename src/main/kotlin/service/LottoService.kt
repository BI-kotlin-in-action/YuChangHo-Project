package service

import domain.Lotto
import domain.LottoResult
import domain.PrizeByRank
import domain.User

class LottoService {

    val validator: Validator = Validator()
    companion object {
        const val MAX_RANK: Int = 4
        const val LOTTO_START_NUM: Int = 1
        const val LOTTO_END_NUM: Int = 45
        const val LOTTO_SIZE: Int = 6

        @JvmStatic
        val NUMBERS = (LOTTO_START_NUM..LOTTO_END_NUM).toList()
    }

    fun moneyCheck(input: String): Int {
        validator.numericCheck(input)

        return input.toInt()
    }

    fun manualCountCheck(input: String, totalCount: Int): Int {
        validator.numericCheck(input)

        val manualCount = input.toInt()

        validator.rangeCheck(manualCount, 0, totalCount)

        return manualCount
    }

    fun manualLottoNumCheck(input: List<String>): Lotto {
        validator.lengthCheck(input, LOTTO_SIZE)
        validator.numericCheck(input)

        val manualLottoNum = input.map { it.toInt() }

        manualLottoNum.forEach { validator.rangeCheck(it, LOTTO_START_NUM, LOTTO_END_NUM) }
        validator.duplicateCheck(manualLottoNum)

        return Lotto(manualLottoNum)
    }
    fun setManualCountAndAutoCount(user: User, totalCount: Int) {
        user.manualLottoCount = manualCountCheck(readln(), totalCount)
        user.autoLottoCount = totalCount - user.manualLottoCount
    }

    fun setManualLottoNum(user: User) {
        val manualLottoNum = manualLottoNumCheck(readln().split(" "))
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
        return shuffledNums.take(LOTTO_SIZE)
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
