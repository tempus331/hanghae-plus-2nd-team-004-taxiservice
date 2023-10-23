package hanghae.four.taxiservice.domain.pay

import hanghae.four.taxiservice.domain.pay.payinfo.PayInfoReader
import hanghae.four.taxiservice.domain.taxi.Taxi
import hanghae.four.taxiservice.domain.taxi.call.Call
import org.springframework.stereotype.Service

@Service
class PaymentService(
    val payInfoReader: PayInfoReader,
    val paymentStore: PaymentStore,
    val payFactory: PayFactory,
) {
    fun pay(command: PaymentCommand, call: Call, taxi: Taxi): Long {
        call.complete()
        taxi.runningComplete()

        if (!command.payType.cashCheck()) {
            val payment = payInfoReader.getPayInfo(requireNotNull(command.payInfoId), command.payType)
            payFactory.execute(payment)
        }

        return requireNotNull(paymentStore.store(command.toEntity()).id)
    }
}
