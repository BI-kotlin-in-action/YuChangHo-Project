package domain

data class Lotto(var winNum: IntArray, var manualLottoCount: Int, var autoLottoCount: Int, val lottoNum: MutableList<IntArray>, var prize: Int, val rank: IntArray)
