package hanghae.four.taxiservice.domain.pay

import hanghae.four.taxiservice.domain.client.ClientReader
import hanghae.four.taxiservice.domain.taxi.call.CallReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class PaymentService(
    val clientReader: ClientReader,
    val callReader: CallReader,
    val paymentStore: PaymentStore,
) {
    fun pay(request: PaymentCommand): Long {
        return requireNotNull(paymentStore.store(request.toEntity()).id)
    }
}
