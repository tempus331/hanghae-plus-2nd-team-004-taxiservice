package hanghae.four.taxiservice.infrastructures.pay.payinfo

import hanghae.four.taxiservice.domain.pay.payinfo.Payment
import org.springframework.data.jpa.repository.JpaRepository

interface PaymentRepository : JpaRepository<Payment, Long>
