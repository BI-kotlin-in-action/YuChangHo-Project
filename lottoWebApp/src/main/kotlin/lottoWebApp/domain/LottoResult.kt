package lottoWebApp.domain

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "lottoresult")
data class LottoResult(
    @Id
    var name: String,
    var firstCount: Int,
    var secondCount: Int,
    var thirdCount: Int,
    var fourthCount: Int,
    var prize: Int
)
