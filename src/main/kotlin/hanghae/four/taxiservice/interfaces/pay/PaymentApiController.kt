package hanghae.four.taxiservice.interfaces.pay

import hanghae.four.taxiservice.applications.pay.PaymentFacade
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class PaymentApiController(
    val paymentFacade: PaymentFacade,
) {

    @PostMapping("/api/v1/pay")
    fun pay(
        @Valid @RequestBody
        request: PayRequest,
    ): PayResponse {
        val paymentHistoryId = paymentFacade.pay(request.toPayCommand())
        return PayResponse(paymentHistoryId = paymentHistoryId)
    }
}
