package domain

import java.util.*

data class Lotto(var winNum: SortedSet<Int>, var manualLottoCount: Int, var autoLottoCount: Int, val lottoNum: MutableList<SortedSet<Int>>, var prize: Int, val rank: IntArray)
