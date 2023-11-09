package domain

data class User(var manualLottoCount: Int, var autoLottoCount: Int, val lottoNum: MutableList<Lotto>)
