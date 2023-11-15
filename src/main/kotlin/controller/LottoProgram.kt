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
    private val user: User = User()
    private val lottoResult: LottoResult = LottoResult()
    private val lottoService: LottoService = LottoService()
    private val systemView: SystemView = SystemView()
    private val winLotto: Lotto = Lotto(sortedSetOf())

    fun run() {
        do {
            start()
            selectManualLottoNum()
            selectAutoLottoNum()
            end()
        } while (isReplay())
    }

    private fun start() {
        systemView.showStartMessage(user.canBuy)
        if (user.canBuy == 0) { user.canBuy = lottoService.canBuyMax() }
        winLotto.addLottoNum(lottoService.makeRandomNum())
    }

    private fun selectManualLottoNum() {
        systemView.showManualLottoBuyMessage(user.canBuy)
        lottoService.setManualCountAndAutoCount(user, user.canBuy)

        for (i in 0 until user.manualLottoCount) {
            systemView.showNumInputMessage(i)
            lottoService.setManualLottoNum(user)
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
            user.canBuy = lottoResult.prize / LOTTO_PRICE
            user.lottoNumListClear()
            winLotto.lottoNumClear()
            lottoResult.rank.clear()
            lottoResult.prize = 0
            return true
        }
        return false
    }
}
