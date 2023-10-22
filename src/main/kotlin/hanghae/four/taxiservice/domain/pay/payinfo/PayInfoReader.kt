package hanghae.four.taxiservice.domain.pay.payinfo

interface PayInfoReader {
    fun getPayInfo(payInfoId: Long, type: PayInfo.Type): PayInfo
}
