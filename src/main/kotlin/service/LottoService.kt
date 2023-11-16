package service

import domain.Lotto
import domain.LottoResult
import domain.PrizeByRank
import domain.User
import view.SystemView

class LottoService {

    val validator: Validator = Validator()
    companion object {
        const val MAX_RANK: Int = 4
        const val LOTTO_START_NUM: Int = 1
        const val LOTTO_END_NUM: Int = 45
        const val LOTTO_SIZE: Int = 6

        @JvmStatic
        val NUMBERS: IntRange = (LOTTO_START_NUM..LOTTO_END_NUM)
    }

    fun canBuyMax(input: String): Int {
        val money = moneyCheck(input)
        return money / SystemView.LOTTO_PRICE
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
        validator.lengthCheck(input)
        validator.numericCheck(input)

        val manualLottoNum = input.map { it.toInt() }

        manualLottoNum.forEach { validator.rangeCheck(it) }
        validator.duplicateCheck(manualLottoNum)

        return Lotto(manualLottoNum)
    }
    fun setManualCountAndAutoCount(input: String, user: User, totalCount: Int) {
        user.manualLottoCount = manualCountCheck(input, totalCount)
        user.autoLottoCount = totalCount - user.manualLottoCount
    }

    fun setManualLottoNum(input: String, user: User) {
        val manualLottoNum = manualLottoNumCheck(input.split(" "))
        user.addLotto(manualLottoNum)
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
        val winNum = winLotto.getLottoNum()

        for (nums in user.getLottoNum()) {
            var count = nums.getLottoNum().intersect(winNum).size

            if (count >= (MAX_RANK - 1)) {
                val rank = PrizeByRank.getRank(count)
                lottoResult.putRank(rank)
            }
        }

        return lottoResult
    }
}
