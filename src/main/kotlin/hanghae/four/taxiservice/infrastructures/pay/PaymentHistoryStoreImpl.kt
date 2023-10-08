package hanghae.four.taxiservice.infrastructures.pay

import hanghae.four.taxiservice.domain.pay.PaymentHistory
import hanghae.four.taxiservice.domain.pay.PaymentHistoryStore
import hanghae.four.taxiservice.utils.annotations.Store

@Store
class PaymentHistoryStoreImpl(
    val paymentHistoryRepository: PaymentHistoryRepository,
) : PaymentHistoryStore {
    override fun store(pamentHistory: PaymentHistory): PaymentHistory {
        return paymentHistoryRepository.save(pamentHistory)
    }
}
