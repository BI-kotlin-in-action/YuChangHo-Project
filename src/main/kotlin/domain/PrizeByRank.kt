package domain

import service.LottoService

enum class PrizeByRank(val rank: Int, val prize: Int) {
    FIRST(1, 100000),
    SECOND(2, 5000),
    THIRD(3, 100),
    FOURTH(4, 5),
    LOSE(5, 0), ;

    companion object {
        @JvmStatic
        fun getRank(count: Int): PrizeByRank {
            return values().find { it.rank == LottoService.LOTTO_SIZE - count + 1 } ?: LOSE
        }
    }
}
