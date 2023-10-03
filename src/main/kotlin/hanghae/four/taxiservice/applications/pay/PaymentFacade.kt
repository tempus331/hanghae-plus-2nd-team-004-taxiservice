package hanghae.four.taxiservice.applications.pay

import hanghae.four.taxiservice.domain.pay.PaymentCommand
import org.springframework.stereotype.Service

@Service
class PaymentFacade {
    fun pay(request: PaymentCommand): Long {
        TODO("Not yet implemented")
    }
}
