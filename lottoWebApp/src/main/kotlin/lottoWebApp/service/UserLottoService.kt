package lottoWebApp.service

import lottoWebApp.domain.UserLotto
import lottoWebApp.repository.UserLottoRepository
import org.springframework.stereotype.Service
import java.util.Collections;

@Service
class UserLottoService ( private val userLottoRepository: UserLottoRepository) {

    companion object {
        private const val LOTTO_START_NUM: Int = 1
        private const val LOTTO_END_NUM: Int = 45
        const val LOTTO_SIZE: Int = 6

        @JvmStatic
        val NUMBERS: IntRange = (LOTTO_START_NUM..LOTTO_END_NUM)

        @JvmStatic
        fun makeRandomNum(): List<Int> {
            val shuffledNums = NUMBERS.shuffled()
            return shuffledNums.take(LOTTO_SIZE)
        }
    }

    fun findByName(name: String): List<UserLotto>{
        return userLottoRepository.findByName(name)
    }
    fun saveManualLottoNum(name: String, lottoNum: String) {
        val manualLotto = UserLotto(name = name, type =  0, lottoNum = lottoNum)
        userLottoRepository.save(manualLotto)
    }

    fun saveAutoLottoNum(name: String, autoCount: Int){
        repeat(autoCount){
            val autoNum = makeRandomNum().joinToString(", ")
            val userLotto = UserLotto(name = name, type = 1, lottoNum = autoNum)
            userLottoRepository.save(userLotto)
        }
    }
}