package domain

class Lotto {
    var winNum = IntArray(6)
    var manualLottoCount: Int = 0
    var autoLottoCount: Int = 0
    val lottoNum = mutableListOf<IntArray>()
}