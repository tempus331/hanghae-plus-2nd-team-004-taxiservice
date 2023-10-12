package hanghae.four.taxiservice.domain.pay

import hanghae.four.taxiservice.domain.pay.payinfo.Payment

interface PayDispatcher {
    fun supports(type: Payment.Type): Boolean
    fun dispatch()
}
