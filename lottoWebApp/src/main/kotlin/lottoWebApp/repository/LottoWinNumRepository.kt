package lottoWebApp.repository

import lottoWebApp.domain.LottoWinNum
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LottoWinNumRepository : JpaRepository<LottoWinNum, Int> {
}