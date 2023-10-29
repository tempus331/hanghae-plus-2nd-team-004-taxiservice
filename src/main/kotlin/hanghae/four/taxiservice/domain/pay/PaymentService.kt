package hanghae.four.taxiservice.domain.pay

import hanghae.four.taxiservice.domain.pay.payinfo.PayInfoReader
import org.springframework.stereotype.Service

@Service
class PaymentService(
    val payInfoReader: PayInfoReader,
    val paymentStore: PaymentStore,
    val payFactory: PayFactory,
) {
    fun pay(command: PaymentCommand): Long {
        if (!command.payType.cashCheck()) {
            val payment = payInfoReader.getPayInfo(requireNotNull(command.payInfoId), command.payType)
            payFactory.execute(payment)
        }

        return requireNotNull(paymentStore.store(command.toEntity()).id)
    }
}
