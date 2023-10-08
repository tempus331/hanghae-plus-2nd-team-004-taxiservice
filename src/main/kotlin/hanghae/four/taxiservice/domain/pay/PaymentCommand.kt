package hanghae.four.taxiservice.domain.pay

import hanghae.four.taxiservice.domain.pay.payinfo.Payment
import java.math.BigDecimal

data class PaymentCommand(
    val clientId: Long,
    val callId: Long,
    val paymentId: Long?,
    val amount: BigDecimal,
    val payType: Payment.Type,
) {
    fun toEntity(): PaymentHistory {
        return PaymentHistory(
            clientId = clientId,
            callId = callId,
            type = payType
        )
    }
}
