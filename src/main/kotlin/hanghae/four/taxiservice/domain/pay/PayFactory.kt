package hanghae.four.taxiservice.domain.pay

import hanghae.four.taxiservice.domain.pay.payinfo.PayInfo
import org.springframework.stereotype.Component

@Component
class PayFactory(
    val payDispatcher: List<PayDispatcher>,
) {
    fun execute(payInfo: PayInfo) {
        val payDispatcher = findPayDispatcher(payInfo.type)
        payDispatcher.dispatch(payInfo)
    }

    private fun findPayDispatcher(type: PayInfo.Type): PayDispatcher {
        return payDispatcher.find { it.supports(type) } ?: throw IllegalArgumentException("지원 하지 않는 호출 타입입니다.")
    }
}
