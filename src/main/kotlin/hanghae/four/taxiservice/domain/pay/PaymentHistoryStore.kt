package hanghae.four.taxiservice.domain.pay

interface PaymentHistoryStore {
    fun store(paymentHistory: PaymentHistory): PaymentHistory
}
