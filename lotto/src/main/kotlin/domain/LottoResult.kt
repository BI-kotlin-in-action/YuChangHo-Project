package domain

data class LottoResult(var prize: Int = 0, private val rank: HashMap<PrizeByRank, Int> = HashMap<PrizeByRank, Int>()) {
    fun getRankCount(prizeByRank: PrizeByRank): Int {
        return rank.getOrDefault(prizeByRank, 0)
    }

    fun putRank(prizeByRank: PrizeByRank) {
        rank[prizeByRank] = rank.getOrDefault(prizeByRank, 0) + 1
    }
    fun rankClear() {
        rank.clear()
    }
}
