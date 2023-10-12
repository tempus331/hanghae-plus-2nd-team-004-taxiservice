package hanghae.four.taxiservice.domain.pay

import hanghae.four.taxiservice.domain.pay.payinfo.Payment
import org.springframework.stereotype.Component

@Component
class PayFactory(
    val payDispatcher: List<PayDispatcher>,
) {
    fun execute(type: Payment.Type) {
        val payDispatcher = findPayDispatcher(type)
        payDispatcher.dispatch()
    }

    private fun findPayDispatcher(type: Payment.Type): PayDispatcher {
        return payDispatcher.find { it.supports(type) } ?: throw IllegalArgumentException("지원 하지 않는 호출 타입입니다.")
    }
}
