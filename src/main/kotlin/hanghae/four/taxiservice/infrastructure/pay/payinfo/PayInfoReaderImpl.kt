package hanghae.four.taxiservice.infrastructure.pay.payinfo

import hanghae.four.taxiservice.domain.pay.payinfo.PayInfo
import hanghae.four.taxiservice.domain.pay.payinfo.PayInfoReader
import hanghae.four.taxiservice.infrastructure.util.fail
import org.springframework.stereotype.Repository

@Repository
class PayInfoReaderImpl(
    val payInfoRepository: PayInfoRepository,
) : PayInfoReader {

    override fun getPayInfo(payInfoId: Long, type: PayInfo.Type): PayInfo {
        return payInfoRepository.findByIdAndType(payInfoId, type) ?: fail()
    }
}
