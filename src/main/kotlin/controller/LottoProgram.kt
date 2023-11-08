package controller

import domain.Lotto
import service.LottoService
import view.SystemView

class LottoProgram {
    val lotto: Lotto = Lotto(IntArray(6), 0, 0, mutableListOf<IntArray>(), 0, IntArray(4))
    val lottoService: LottoService = LottoService()
    val systemView: SystemView = SystemView()

    fun run() {
        start()
        selectLottoNum()
        end()
        replay()
    }

    fun start() {
        systemView.showStartMessage()
    }

    fun selectLottoNum() {
        systemView.showManualLottoBuyMessage()
        lottoService.setManualCountAndAutoCount(lotto, systemView.totalCount)
        println(lotto.manualLottoCount)
        println(lotto.autoLottoCount)
        println(systemView.totalCount)

        lotto.lottoNum.clear()
        for (i in 0 until lotto.manualLottoCount) {
            systemView.showNumInputMessage(i)
            lottoService.setManualLottoNum(lotto)
        }

        lotto.winNum = lottoService.makeRandomNum()
        systemView.showStartAutoLottoMessage()

        for (i in 0 until lotto.autoLottoCount) {
            lotto.lottoNum.add(lottoService.makeRandomNum())
        }
        systemView.showFinishAutoLottoMessage()
    }

    fun end() {
        systemView.showLottoNumMessage()
        lottoService.showLottoNum(lotto)
        systemView.showWinNumMessage(lotto.winNum)
        systemView.showLottoResultMessage(lottoService.getResult(lotto))
    }

    fun replay() {
        if (lotto.prize >= 1000) {
            systemView.showRestartLottoMessage(lotto)
            run()
        }
    }
}
