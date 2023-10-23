package hanghae.four.taxiservice.infrastructure.pay.dispatcher

import hanghae.four.taxiservice.domain.pay.PayDispatcher
import hanghae.four.taxiservice.domain.pay.payinfo.PayInfo
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class KakaoPayPayDispatcher : PayDispatcher {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun supports(type: PayInfo.Type): Boolean {
        return PayInfo.Type.KAKAOPAY == type
    }

    override fun dispatch(payment: PayInfo) {
        log.info("카카오 페이 결제")
    }
}
