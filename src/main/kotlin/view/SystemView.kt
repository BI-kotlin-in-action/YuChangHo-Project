package view

import domain.Lotto

class SystemView {

    var totalCount: Int = 0

    fun canBuyMax() {
        val money = readLine()!!.toInt()
        this.totalCount = money / 1000
    }
    fun showStartMessage() {
        println("로또 금액은 1000원이며 로또 구매에 사용할 금액을 입력해주세요.")
    }

    fun showManualLottoBuyMessage() {
        if (totalCount == 0) {
            canBuyMax()
        }
        println("로또를 $totalCount 장 만큼 구매할 수 있습니다.")
        println("수동으로 몇 장 구매하시겠습니까? 나머지는 모두 자동으로 구매합니다.")
    }

    fun showNumInputMessage(count: Int) {
        println(" ${count + 1}번째 수동 로또입니다. 1~45사이의 숫자 6개를 입력해주세요. (숫자는 공백으로 구분합니다.)")
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

    fun showWinNumMessage(winNum: IntArray) {
        println("로또 당첨번호를 알려드립니다.")
        println("로또 당첨번호 = ${winNum.contentToString()}")
    }

    fun showLottoResultMessage(lotto: Lotto) {
        println("로또 당첨결과")
        lotto.rank.forEachIndexed { index, i ->
            when (index) {
                0 -> lotto.prize += i * 100000 * 1000
                1 -> lotto.prize += i * 5000 * 1000
                2 -> lotto.prize += i * 100 * 1000
                3 -> lotto.prize += i * 5 * 1000
            }
            println("${index + 1}등 : ${i}회")
        }
        println("총상금 : ${lotto.prize / 1000}KW ")
    }

    fun showRestartLottoMessage(lotto: Lotto) {
        println("당첨된 로또 금액으로 다시 로또를 삽니다 ")
        println("당첨된 로또 금액은 ${lotto.prize}원 입니다.")

        totalCount = lotto.prize / 1000
    }
}
