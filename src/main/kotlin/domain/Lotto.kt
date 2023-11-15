package domain

import java.util.SortedSet

data class Lotto(private val num: SortedSet<Int>) {
    constructor(num: List<Int>) : this(num.toSortedSet())

    fun getLottoNum(): Set<Int> {
        return num
    }

    fun lottoNumClear() {
        num.clear()
    }

    fun addLottoNum(lottoNum: List<Int>) {
        num.addAll(lottoNum)
    }
}
