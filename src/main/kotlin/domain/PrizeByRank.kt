package domain

enum class PrizeByRank(val rank: Int, val correctCount: Int, val prize: Int) {
    FIRST(1, 6, 100000),
    SECOND(2, 5, 5000),
    THIRD(3, 4, 100),
    FOURTH(4, 3, 5),
    LOSE(5, 2, 0), ;

    companion object {
        @JvmStatic
        fun getRank(count: Int): PrizeByRank {
            return values().find { it.correctCount == count } ?: LOSE
        }
    }
}
