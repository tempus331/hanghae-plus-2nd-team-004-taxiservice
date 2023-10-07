package hanghae.four.taxiservice.unit.domain.pay

import hanghae.four.taxiservice.domain.client.ClientReader
import hanghae.four.taxiservice.domain.pay.Payment
import hanghae.four.taxiservice.domain.pay.PaymentCommand
import hanghae.four.taxiservice.domain.pay.PaymentService
import hanghae.four.taxiservice.domain.taxi.call.CallReader
import hanghae.four.taxiservice.unit.infrastructures.call.FakeCallRepository
import hanghae.four.taxiservice.unit.infrastructures.client.FakeClientRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class PaymentServiceTest {

    private lateinit var paymentService: PaymentService
    private lateinit var clientReader: ClientReader
    private lateinit var callReader: CallReader

    @BeforeEach
    fun setup() {
        clientReader = FakeClientRepository()
        callReader = FakeCallRepository()

        paymentService = PaymentService(clientReader, callReader)
    }

    @Test
    fun `택시 현금 결제 성공`() {
        val request = PaymentCommand(
            clientId = 1L,
            callId = 1L,
            amount = BigDecimal(1000),
            payType = Payment.Type.CASH,
            pgType = null
        )

        val payId = paymentService.pay(request)

        assertThat(payId).isEqualTo(1L)
    }
}
