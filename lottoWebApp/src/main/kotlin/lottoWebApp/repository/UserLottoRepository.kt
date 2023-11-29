package lottoWebApp.repository

import lottoWebApp.domain.UserLotto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserLottoRepository: JpaRepository<UserLotto, String>{
    fun findByName (name: String): List<UserLotto>
}