package hanghae.four.taxiservice.infrastructures.pay

import hanghae.four.taxiservice.domain.pay.Payment
import hanghae.four.taxiservice.domain.pay.PaymentStore
import hanghae.four.taxiservice.utils.annotations.Store

@Store
class PaymentStoreImpl : PaymentStore {
    override fun store(payment: Payment): Payment {
        TODO("Not yet implemented")
    }
}
