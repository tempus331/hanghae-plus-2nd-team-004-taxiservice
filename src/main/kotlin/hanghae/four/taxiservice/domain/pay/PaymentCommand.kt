package hanghae.four.taxiservice.domain.pay

import hanghae.four.taxiservice.domain.pay.payinfo.PayInfo
import java.math.BigDecimal

data class PaymentCommand(
    val clientId: Long,
    val callId: Long,
    val payInfoId: Long?,
    val amount: BigDecimal,
    val payType: PayInfo.Type,
) {
    fun toEntity(): Payment {
        return Payment(
            clientId = clientId,
            callId = callId,
            type = payType,
            amount = amount
        )
    }
}
