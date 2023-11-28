package lottoWebApp.domain

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "userlotto")
data class UserLotto(
    @Id
    var user: String,
    var type: Int,
    var lottoNum: String
)
