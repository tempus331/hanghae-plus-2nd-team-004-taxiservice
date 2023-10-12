package hanghae.four.taxiservice.infrastructures.pay.dispatcher

import hanghae.four.taxiservice.domain.pay.PayDispatcher
import hanghae.four.taxiservice.domain.pay.payinfo.Payment
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class LotteCardPayDispatcher : PayDispatcher {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun supports(type: Payment.Type): Boolean {
        return Payment.Type.LOTTECARD == type
    }

    override fun dispatch() {
        log.info("롯데 카드 결제")
    }
}
