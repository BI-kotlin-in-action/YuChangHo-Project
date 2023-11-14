package domain

import java.util.SortedSet

data class Lotto(private val num: SortedSet<Int>) {
    constructor(num: List<Int>) : this(num.toSortedSet())

    fun getLottoNum(): SortedSet<Int> {
        return num
    }
}
