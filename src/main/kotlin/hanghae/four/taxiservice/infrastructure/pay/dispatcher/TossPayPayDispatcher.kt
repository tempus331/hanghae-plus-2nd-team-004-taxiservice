package hanghae.four.taxiservice.infrastructure.pay.dispatcher

import hanghae.four.taxiservice.domain.pay.PayDispatcher
import hanghae.four.taxiservice.domain.pay.payinfo.Payment
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class TossPayPayDispatcher : PayDispatcher {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun supports(type: Payment.Type): Boolean {
        return Payment.Type.TOSSPAY == type
    }

    override fun dispatch() {
        log.info("토스 페이 결제")
    }
}
