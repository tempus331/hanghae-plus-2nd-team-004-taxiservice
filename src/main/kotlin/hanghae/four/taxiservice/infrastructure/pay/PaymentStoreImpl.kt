package hanghae.four.taxiservice.infrastructure.pay

import hanghae.four.taxiservice.domain.pay.Payment
import hanghae.four.taxiservice.domain.pay.PaymentStore
import hanghae.four.taxiservice.util.annotations.Store

@Store
class PaymentStoreImpl(
    val paymentRepository: PaymentRepository,
) : PaymentStore {
    override fun store(pament: Payment): Payment {
        return paymentRepository.save(pament)
    }
}
