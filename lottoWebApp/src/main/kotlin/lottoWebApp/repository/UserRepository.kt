package lottoWebApp.repository

import lottoWebApp.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Int>{
    fun findByName (name: String): List<User>
}