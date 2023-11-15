package domain

data class User(var canBuy: Int = 0, var manualLottoCount: Int = 0, var autoLottoCount: Int = 0, private val lottoNum: MutableList<Lotto> = mutableListOf<Lotto>()) {
    fun addLotto(lotto: Lotto) {
        lottoNum.add(lotto)
    }

    fun lottoClear() {
        lottoNum.clear()
    }

    fun getLottoNum(): MutableList<Lotto> {
        return lottoNum
    }
}
