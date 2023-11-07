package view

class SystemView {
    fun showStartMessage() {
        println("로또 금액은 1000원이며 로또 구매에 사용할 금액을 입력해주세요.")
    }

    fun showManualLottoBuyMessage(count: Int) {
        println("로또를 $count 장 만큼 구매할 수 있습니다.")
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

    fun showLottoResultMessage(rank: IntArray) {
        var prize = 0

        println("로또 당첨결과")
        rank.forEachIndexed { index, i ->
            when (index) {
                0 -> prize += i * 100000
                1 -> prize += i * 5000
                2 -> prize += i * 100
                3 -> prize += i * 5
            }
            println("${index + 1}등 : ${i}회")
        }
        println("총상금 : ${prize}KW ")
    }
}