package service

class Validator {

    fun numericCheck(input: String) {
        try {
            input.toInt()
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("입력값은 숫자가 아닙니다.")
        }
    }

    fun numericCheck(input: List<String>) {
        try {
            input.forEach { it.toInt() }
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("입력값은 숫자가 아닙니다.")
        }
    }

    fun lengthCheck(input: List<String>, size: Int) {
        if (input.size != size) {
            throw IllegalArgumentException("입력하신 숫자의 개수가 ${size}이 아닙니다.")
        }
    }

    fun duplicateCheck(input: List<Int>) {
        if (input.size != input.distinct().size) {
            throw IllegalArgumentException("중복된 숫자가 있습니다.")
        }
    }

    fun rangeCheck(input: Int, startNum: Int, endNum: Int) {
        if (input < startNum || input > endNum) {
            throw IllegalArgumentException("입력하신 숫자가 범위를 벗어났습니다.")
        }
    }
}
