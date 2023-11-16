package controller

import domain.Lotto
import domain.LottoResult
import domain.User
import service.LottoService
import view.SystemView

class LottoProgram {
    companion object {
        const val LOTTO_PRICE: Int = 1000
    }
    private lateinit var user: User
    private val lottoResult: LottoResult = LottoResult()
    private val lottoService: LottoService = LottoService()
    private val systemView: SystemView = SystemView()
    private val winLotto: Lotto = Lotto()
    private var canBuy = 0

    fun run() {
        do {
            start()
            selectManualLottoNum()
            selectAutoLottoNum()
            end()
        } while (isReplay())
    }

    private fun start() {
        systemView.showStartMessage(canBuy)
        if (canBuy == 0) { val money = systemView.readUserInput()
            canBuy = lottoService.canBuyMax(money) }

        winLotto.addLottoNum(lottoService.makeRandomNum())
    }

    private fun selectManualLottoNum() {
        systemView.showManualLottoBuyMessage(canBuy)
        val manualLottoCountInput = systemView.readUserInput()
        val (manualLottoCount, autoLottoCount) = lottoService.setManualCountAndAutoCount(manualLottoCountInput, canBuy)
        user = User(canBuy, manualLottoCount, autoLottoCount)

        for (i in 0 until user.manualLottoCount) {
            systemView.showNumInputMessage(i)
            val manualLottoNum = systemView.readUserInput()

            lottoService.setManualLottoNum(manualLottoNum, user)
        }
    }

    private fun selectAutoLottoNum() {
        systemView.showStartAutoLottoMessage()

        repeat(user.autoLottoCount) {
            user.addLotto(Lotto(lottoService.makeRandomNum()))
        }

        systemView.showFinishAutoLottoMessage()
    }

    private fun end() {
        systemView.showLottoNumMessage(user)
        systemView.showWinNumMessage(winLotto.getLottoNum())
        systemView.showLottoResultMessage(lottoService.getResult(lottoResult, winLotto, user))
    }

    private fun isReplay(): Boolean {
        if (lottoResult.prize >= LOTTO_PRICE) {
            systemView.showRestartLottoMessage(lottoResult)
            canBuy = lottoResult.prize / LOTTO_PRICE
            user.lottoNumListClear()
            winLotto.lottoNumClear()
            lottoResult.rankClear()
            lottoResult.prize = 0
            return true
        }
        return false
    }
}
