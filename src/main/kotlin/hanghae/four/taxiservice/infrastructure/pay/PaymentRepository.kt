package hanghae.four.taxiservice.infrastructure.pay

import hanghae.four.taxiservice.domain.pay.Payment
import org.springframework.data.jpa.repository.JpaRepository

interface PaymentRepository : JpaRepository<Payment, Long>
