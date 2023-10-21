package hanghae.four.taxiservice.infrastructure.pay.payinfo

import hanghae.four.taxiservice.domain.pay.payinfo.Payment
import org.springframework.data.jpa.repository.JpaRepository

interface PaymentRepository : JpaRepository<Payment, Long> {
    fun findByIdAndType(paymentId: Long, type: Payment.Type): Payment?
}
