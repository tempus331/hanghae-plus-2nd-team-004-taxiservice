package hanghae.four.taxiservice.infrastructure.pay.dispatcher

import hanghae.four.taxiservice.domain.pay.PayDispatcher
import hanghae.four.taxiservice.domain.pay.payinfo.PayInfo
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class TossPayPayDispatcher : PayDispatcher {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun supports(type: PayInfo.Type): Boolean {
        return PayInfo.Type.TOSSPAY == type
    }

    override fun dispatch(payment: PayInfo) {
        log.info("토스 페이 결제")
    }
}
