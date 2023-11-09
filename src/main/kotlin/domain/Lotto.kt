package domain

import java.util.SortedSet

data class Lotto(val num: SortedSet<Int>) {
    constructor(num: List<Int>) : this(num.toSortedSet())
}
