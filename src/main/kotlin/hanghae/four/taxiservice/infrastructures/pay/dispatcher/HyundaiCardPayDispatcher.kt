package hanghae.four.taxiservice.infrastructures.pay.dispatcher

import hanghae.four.taxiservice.domain.pay.PayDispatcher
import hanghae.four.taxiservice.domain.pay.payinfo.Payment
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class HyundaiCardPayDispatcher : PayDispatcher {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun supports(type: Payment.Type): Boolean {
        return Payment.Type.HYUNDAICARD == type
    }

    override fun dispatch() {
        log.info("현대 카드 결제")
    }
}
