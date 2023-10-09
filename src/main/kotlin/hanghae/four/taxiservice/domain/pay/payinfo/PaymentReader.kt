package hanghae.four.taxiservice.domain.pay.payinfo

interface PaymentReader {
    fun getPayment(paymentId: Long, type: Payment.Type): Payment
}
