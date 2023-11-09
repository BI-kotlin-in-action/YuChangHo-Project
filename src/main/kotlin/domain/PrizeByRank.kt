package domain

enum class PrizeByRank(val rank: Int, val prize: Int) {
    FIRST(1, 100000),
    SECOND(2, 5000),
    THIRD(3, 100),
    FOURTH(4, 5),
}
