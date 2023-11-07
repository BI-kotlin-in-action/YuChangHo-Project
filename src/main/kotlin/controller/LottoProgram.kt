package controller

import domain.Lotto
import service.LottoService
import view.SystemView

class LottoProgram {
    val lotto = Lotto()
    val lottoService: LottoService
    val systemView: SystemView

    init {
        lottoService = LottoService()
        systemView = SystemView()
    }

    fun run() {
        systemView.showStartMessage()
        systemView.showManualLottoBuyMessage(lottoService.canBuyMax())
        lottoService.setManualCountAndAutoCount(lotto)

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
        systemView.showLottoNumMessage()
        lottoService.showLottoNum(lotto)
        systemView.showWinNumMessage(lotto.winNum)
        systemView.showLottoResultMessage(lottoService.getResult(lotto))
    }
}