package hanghae.four.taxiservice.applications.pay

import hanghae.four.taxiservice.domain.pay.PaymentCommand
import hanghae.four.taxiservice.domain.pay.PaymentService
import hanghae.four.taxiservice.utils.annotations.Facade

@Facade
class PaymentFacade(
    val paymentService: PaymentService,
) {
    fun pay(request: PaymentCommand): Long {
        return paymentService.pay(request)
    }
}
