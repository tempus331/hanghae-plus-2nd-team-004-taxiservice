package hanghae.four.taxiservice.domain.pay

interface PaymentStore {
    fun store(payment: Payment): Payment
}
