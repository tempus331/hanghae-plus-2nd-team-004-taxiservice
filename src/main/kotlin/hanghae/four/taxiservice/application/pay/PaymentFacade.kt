package hanghae.four.taxiservice.application.pay

import hanghae.four.taxiservice.domain.pay.PaymentCommand
import hanghae.four.taxiservice.domain.pay.PaymentService
import hanghae.four.taxiservice.domain.taxi.TaxiService
import hanghae.four.taxiservice.domain.taxi.call.CallService
import hanghae.four.taxiservice.global.annotations.Facade
import org.springframework.transaction.annotation.Transactional

@Transactional
@Facade
class PaymentFacade(
    val callService: CallService,
    val taxiService: TaxiService,
    val paymentService: PaymentService,
) {
    fun pay(command: PaymentCommand): Long {
        val call = callService.findCall(command.callId)
        val taxi = taxiService.findTaxi(call.taxiId)
        return paymentService.pay(command, call, taxi)
    }
}
