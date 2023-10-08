package hanghae.four.taxiservice.domain.pay

import java.math.BigDecimal

data class PaymentCommand(
    val clientId: Long,
    val callId: Long,
    val amount: BigDecimal,
    val payType: Payment.Type,
) {
    fun toEntity(): Payment {
        return Payment(
            clientId = clientId,
            callId = callId,
            type = payType
        )
    }
}
