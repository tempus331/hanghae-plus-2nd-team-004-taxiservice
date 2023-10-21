package hanghae.four.taxiservice.infrastructure.pay

import hanghae.four.taxiservice.domain.pay.PaymentHistory
import org.springframework.data.jpa.repository.JpaRepository

interface PaymentHistoryRepository : JpaRepository<PaymentHistory, Long>
