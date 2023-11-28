package lottoWebApp.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "lottowinnum")
data class LottoWinNum(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,
    var winNum: String
)
