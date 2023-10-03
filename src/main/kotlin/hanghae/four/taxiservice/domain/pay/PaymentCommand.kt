package hanghae.four.taxiservice.domain.pay

import java.math.BigDecimal

data class PaymentCommand(
    val clientId: Long,
    val taxiId: Long,
    val amount: BigDecimal,
    val payType: Payment.Type,
    val pgType: Payment.Type?,
) {

}
