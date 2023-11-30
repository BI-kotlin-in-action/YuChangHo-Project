package lottoWebApp.repository

import lottoWebApp.domain.LottoResult
import lottoWebApp.domain.UserLotto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LottoResultRepository: JpaRepository<LottoResult, String> {
}