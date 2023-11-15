package domain

data class LottoResult(var prize: Int = 0, val rank: HashMap<PrizeByRank, Int> = HashMap<PrizeByRank, Int>())
