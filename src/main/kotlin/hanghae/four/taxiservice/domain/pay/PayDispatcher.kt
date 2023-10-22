package hanghae.four.taxiservice.domain.pay

import hanghae.four.taxiservice.domain.pay.payinfo.PayInfo

interface PayDispatcher {
    fun supports(type: PayInfo.Type): Boolean
    fun dispatch(payment: PayInfo)
}
