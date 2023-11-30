package lottoWebApp.domain

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "lottoresult")
data class LottoResult(
    @Id
    var name: String,
    var firstCount: Int = 0,
    var secondCount: Int = 0,
    var thirdCount: Int = 0,
    var fourthCount: Int = 0,
    var prize: Int = 0
){
    companion object {
        const val KW: Int = 1000
    }
    fun addRankCount(rank: PrizeByRank) {
        when (rank) {
            PrizeByRank.FIRST -> {
                firstCount++
            }
            PrizeByRank.SECOND -> {
                secondCount++
            }
            PrizeByRank.THIRD -> {
                thirdCount++
            }
            PrizeByRank.FOURTH -> {
                fourthCount++
            }
        }
    }

    fun addPrize(rank: PrizeByRank){
        when (rank) {
            PrizeByRank.FIRST -> {
                prize += firstCount * rank.prize * KW
            }
            PrizeByRank.SECOND -> {
                prize += secondCount * rank.prize * KW
            }
            PrizeByRank.THIRD -> {
                prize += thirdCount * rank.prize * KW
            }
            PrizeByRank.FOURTH -> {
                prize += fourthCount * rank.prize * KW
            }
        }
    }

}
