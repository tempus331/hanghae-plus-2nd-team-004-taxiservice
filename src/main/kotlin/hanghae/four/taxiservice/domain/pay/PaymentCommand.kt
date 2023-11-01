package hanghae.four.taxiservice.domain.pay

import hanghae.four.taxiservice.domain.pay.payinfo.PayInfo
import java.math.BigDecimal

data class PaymentCommand(
    val clientId: Long,
    val callNumber: String,
    val payInfoId: Long?,
    val amount: BigDecimal,
    val payType: PayInfo.Type,
) {
    fun toEntity(callId: Long): Payment {
        return Payment(
            clientId = clientId,
            callId = callId,
            type = payType,
            amount = amount
        )
    }
}
