package hanghae.four.taxiservice.infrastructure.pay

import hanghae.four.taxiservice.domain.pay.PaymentHistory
import hanghae.four.taxiservice.domain.pay.PaymentHistoryStore
import hanghae.four.taxiservice.util.annotations.Store

@Store
class PaymentHistoryStoreImpl(
    val paymentHistoryRepository: PaymentHistoryRepository,
) : PaymentHistoryStore {
    override fun store(pamentHistory: PaymentHistory): PaymentHistory {
        return paymentHistoryRepository.save(pamentHistory)
    }
}
