package controller

import domain.Lotto
import domain.LottoResult
import domain.User
import service.LottoService
import view.SystemView

class LottoProgram {
    companion object {
        const val MAX_RANK: Int = 4
        const val LOTTO_PRICE: Int = 1000
    }
    private val user: User = User(0, 0, mutableListOf<Lotto>())
    private val lottoResult: LottoResult = LottoResult(0, IntArray(MAX_RANK))
    private val lottoService: LottoService = LottoService()
    private val systemView: SystemView = SystemView()
    private val winLotto: Lotto = lottoService.makeRandomNum()

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
        lottoService.setManualCountAndAutoCount(user, systemView.canBuy)
        user.lottoNum.clear()

        for (i in 0 until user.manualLottoCount) {
            systemView.showNumInputMessage(i)
            lottoService.setManualLottoNum(user)
        }

        systemView.showStartAutoLottoMessage()

        repeat(user.autoLottoCount) {
            user.lottoNum.add(lottoService.makeRandomNum())
        }

        systemView.showFinishAutoLottoMessage()
    }

    fun end() {
        systemView.showLottoNumMessage()
        lottoService.showLottoNum(user)
        systemView.showWinNumMessage(winLotto.num)
        systemView.showLottoResultMessage(lottoService.getResult(lottoResult, winLotto, user))
    }

    fun replay() {
        if (lottoResult.prize >= LOTTO_PRICE) {
            systemView.showRestartLottoMessage(lottoResult)
            run()
        }
    }
}
