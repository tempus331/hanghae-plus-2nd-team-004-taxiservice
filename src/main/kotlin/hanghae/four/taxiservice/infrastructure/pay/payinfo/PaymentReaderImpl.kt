package hanghae.four.taxiservice.infrastructure.pay.payinfo

import hanghae.four.taxiservice.domain.pay.payinfo.Payment
import hanghae.four.taxiservice.domain.pay.payinfo.PaymentReader
import hanghae.four.taxiservice.infrastructure.util.fail
import org.springframework.stereotype.Repository

@Repository
class PaymentReaderImpl(
    val paymentRepository: PaymentRepository,
) : PaymentReader {

    override fun getPayment(paymentId: Long, type: Payment.Type): Payment {
        return paymentRepository.findByIdAndType(paymentId, type) ?: fail()
    }
}
