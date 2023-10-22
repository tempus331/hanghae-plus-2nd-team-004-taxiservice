package hanghae.four.taxiservice.domain.pay

import hanghae.four.taxiservice.domain.pay.payinfo.Payment
import hanghae.four.taxiservice.domain.pay.payinfo.PaymentReader
import hanghae.four.taxiservice.domain.taxi.Taxi
import hanghae.four.taxiservice.domain.taxi.call.Call
import org.springframework.stereotype.Service

@Service
class PaymentService(
    val paymentReader: PaymentReader,
    val paymentHistoryStore: PaymentHistoryStore,
    val payFactory: PayFactory,
) {
    fun pay(command: PaymentCommand, call: Call, taxi: Taxi): Long {
        call.complete()
        taxi.runningComplete()

        if (command.payType != Payment.Type.CASH) {
            paymentReader.getPayment(requireNotNull(command.paymentId), command.payType)
        }

        payFactory.execute(command.payType)

        return requireNotNull(paymentHistoryStore.store(command.toEntity()).id)
    }
}
