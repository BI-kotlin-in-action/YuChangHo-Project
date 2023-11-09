package domain

data class LottoResult(var prize: Int, val rank: HashMap<PrizeByRank, Int>)
