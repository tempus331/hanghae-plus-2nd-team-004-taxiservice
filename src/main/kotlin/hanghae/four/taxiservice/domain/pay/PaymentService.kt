package hanghae.four.taxiservice.domain.pay

import hanghae.four.taxiservice.domain.pay.payinfo.Payment
import hanghae.four.taxiservice.domain.pay.payinfo.PaymentReader
import hanghae.four.taxiservice.domain.taxi.TaxiReader
import hanghae.four.taxiservice.domain.taxi.call.CallReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class PaymentService(
    val callReader: CallReader,
    val taxiReader: TaxiReader,
    val paymentReader: PaymentReader,
    val paymentHistoryStore: PaymentHistoryStore,
    val payFactory: PayFactory,
) {
    fun pay(command: PaymentCommand): Long {
        val call = callReader.getById(command.callId)
        call.complete()

        val taxi = taxiReader.getTaxi(call.taxiId)
        taxi.runningComplete()

        if (command.payType != Payment.Type.CASH) {
            paymentReader.getPayment(requireNotNull(command.paymentId), command.payType)
        }

        payFactory.execute(command.payType)

        return requireNotNull(paymentHistoryStore.store(command.toEntity()).id)
    }
}
