package hanghae.four.taxiservice.application.pay

import hanghae.four.taxiservice.domain.pay.PaymentCommand
import hanghae.four.taxiservice.domain.pay.PaymentService
import hanghae.four.taxiservice.util.annotations.Facade

@Facade
class PaymentFacade(
    val paymentService: PaymentService,
) {
    fun pay(command: PaymentCommand): Long {
        return paymentService.pay(command)
    }
}
