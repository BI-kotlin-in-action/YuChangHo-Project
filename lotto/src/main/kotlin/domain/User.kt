package domain

data class User(var canBuy: Int, var manualLottoCount: Int, var autoLottoCount: Int) {

    private val lottoNumList: MutableList<Lotto> = mutableListOf()
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
