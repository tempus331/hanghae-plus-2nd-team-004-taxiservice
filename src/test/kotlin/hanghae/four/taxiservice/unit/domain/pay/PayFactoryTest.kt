package hanghae.four.taxiservice.unit.domain.pay

import hanghae.four.taxiservice.domain.pay.PayFactory
import hanghae.four.taxiservice.domain.pay.payinfo.Payment
import hanghae.four.taxiservice.infrastructure.pay.dispatcher.CashPayDispatcher
import hanghae.four.taxiservice.infrastructure.pay.dispatcher.SamsungCardPayDispatcher
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class PayFactoryTest {

    @Test
    fun `결제 타입에 따른 결제 api 선택`() {
        val payFactory: PayFactory = PayFactory(listOf(SamsungCardPayDispatcher(), CashPayDispatcher()))

        payFactory.execute(Payment.Type.CASH)
    }

    @Test
    fun `결제 api 선택 중 해당 결제 타입이 없다면 에러 발생`() {
        val payFactory: PayFactory = PayFactory(listOf(SamsungCardPayDispatcher(), CashPayDispatcher()))

        Assertions.assertThatThrownBy { payFactory.execute(Payment.Type.LOTTECARD) }
            .isInstanceOf(java.lang.IllegalArgumentException::class.java)
    }
}
