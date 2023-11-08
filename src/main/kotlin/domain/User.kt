package domain

import java.util.*

data class User(var manualLottoCount: Int, var autoLottoCount: Int, val lottoNum: MutableList<SortedSet<Int>>)
