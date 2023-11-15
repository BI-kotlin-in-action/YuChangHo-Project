package domain

data class User(var canBuy: Int = 0, var manualLottoCount: Int = 0, var autoLottoCount: Int = 0, private val lottoNumList: MutableList<Lotto> = mutableListOf<Lotto>()) {
    fun addLotto(lotto: Lotto) {
        lottoNumList.add(lotto)
    }

    fun lottoNumListClear() {
        lottoNumList.clear()
    }

    fun getLottoNum(): List<Lotto> {
        return lottoNumList
    }
}
