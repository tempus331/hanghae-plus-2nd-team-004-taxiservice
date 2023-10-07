package hanghae.four.taxiservice.interfaces.pay

import hanghae.four.taxiservice.domain.pay.Payment
import hanghae.four.taxiservice.domain.pay.PaymentCommand
import java.math.BigDecimal
import javax.validation.constraints.NotNull

data class PayRequest(
    @field: NotNull(message = "사용자 id를 입력해주세요.")
    val clientId: Long,

    @field: NotNull(message = "택시 호출 id를 입력해주세요.")
    val callId: Long,

    @field: NotNull(message = "결제 요금을 입력해주세요.")
    val amount: BigDecimal,

    @field: NotNull(message = "결제를 선택해주세요.")
    val payType: Payment.Type,

    val pgType: Payment.Type?,
) {
    fun toPayCommand(): PaymentCommand {
        return PaymentCommand(
            clientId = clientId,
            callId = callId,
            amount = amount,
            payType = payType,
            pgType = pgType
        )
    }
}

data class PayResponse(val paymentId: Long)
