package view

import domain.Lotto
import java.util.*

class SystemView {
    companion object {
        const val LOTTO_PRICE: Int = 1000
        const val KW: Int = 1000
        const val LOTTO_START_NUM: Int = 1
        const val LOTTO_END_NUM: Int = 45
        const val LOTTO_SIZE: Int = 6
    }
    var canBuy: Int = 0

    fun canBuyMax() {
        val money = readLine()!!.toInt()
        this.canBuy = money / LOTTO_PRICE
    }
    fun showStartMessage() {
        println("로또 금액은 ${LOTTO_PRICE}원이며 로또 구매에 사용할 금액을 입력해주세요.")
    }

    fun showManualLottoBuyMessage() {
        if (canBuy == 0) {
            canBuyMax()
        }
        println("로또를 $canBuy 장 만큼 구매할 수 있습니다.")
        println("수동으로 몇 장 구매하시겠습니까? 나머지는 모두 자동으로 구매합니다.")
    }

    fun showNumInputMessage(count: Int) {
        println(" ${count + 1}번째 수동 로또입니다. $LOTTO_START_NUM~${LOTTO_END_NUM}사이의 숫자 ${LOTTO_SIZE}개를 입력해주세요. (숫자는 공백으로 구분합니다.)")
    }

    fun showStartAutoLottoMessage() {
        println("자동 로또 번호를 고르겠습니다. ")
    }

    fun showFinishAutoLottoMessage() {
        println("자동 로또 번호 고르기를 완료했습니다. ")
    }

    fun showLottoNumMessage() {
        println("구매하신 로또 번호를 알려드립니다.")
    }

    fun showWinNumMessage(winNum: SortedSet<Int>) {
        println("로또 당첨번호를 알려드립니다.")
        println("로또 당첨번호 = $winNum")
    }

    fun showLottoResultMessage(lotto: Lotto) {
        println("로또 당첨결과")
        lotto.rank.forEachIndexed { index, i ->
            when (index) {
                0 -> lotto.prize += i * 100000 * KW
                1 -> lotto.prize += i * 5000 * KW
                2 -> lotto.prize += i * 100 * KW
                3 -> lotto.prize += i * 5 * KW
            }
            println("${index + 1}등 : ${i}회")
        }
        println("총상금 : ${lotto.prize / KW}KW ")
    }

    fun showRestartLottoMessage(lotto: Lotto) {
        println("당첨된 로또 금액으로 다시 로또를 삽니다 ")
        println("당첨된 로또 금액은 ${lotto.prize}원 입니다.")

        canBuy = lotto.prize / LOTTO_PRICE
    }
}
