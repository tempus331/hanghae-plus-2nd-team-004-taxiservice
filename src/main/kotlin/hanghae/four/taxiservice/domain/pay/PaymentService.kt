package hanghae.four.taxiservice.domain.pay

import hanghae.four.taxiservice.domain.client.ClientReader
import hanghae.four.taxiservice.domain.pay.payinfo.Payment
import hanghae.four.taxiservice.domain.pay.payinfo.PaymentReader
import hanghae.four.taxiservice.domain.taxi.call.CallReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class PaymentService(
    val clientReader: ClientReader,
    val callReader: CallReader,
    val paymentReader: PaymentReader,
    val PaymentHistoryStore: PaymentHistoryStore,
    val payFactory: PayFactory,
) {
    fun pay(request: PaymentCommand): Long {
        clientReader.getClient(request.clientId)
        callReader.getBy(request.callId)

        if (request.payType != Payment.Type.CASH) {
            paymentReader.getPayment(requireNotNull(request.paymentId), request.payType)
        }

        payFactory.execute(request.payType)

        return requireNotNull(PaymentHistoryStore.store(request.toEntity()).id)
    }
}
