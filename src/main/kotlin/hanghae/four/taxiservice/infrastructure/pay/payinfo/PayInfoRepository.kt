package hanghae.four.taxiservice.infrastructure.pay.payinfo

import hanghae.four.taxiservice.domain.pay.payinfo.PayInfo
import org.springframework.data.jpa.repository.JpaRepository

interface PayInfoRepository : JpaRepository<PayInfo, Long> {
    fun findByIdAndType(paymentId: Long, type: PayInfo.Type): PayInfo?
}
