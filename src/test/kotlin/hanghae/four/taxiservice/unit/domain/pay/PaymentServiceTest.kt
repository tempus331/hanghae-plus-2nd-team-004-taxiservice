package hanghae.four.taxiservice.unit.domain.pay

import hanghae.four.taxiservice.domain.client.Client
import hanghae.four.taxiservice.domain.pay.PaymentCommand
import hanghae.four.taxiservice.domain.pay.PaymentHistoryStore
import hanghae.four.taxiservice.domain.pay.PaymentService
import hanghae.four.taxiservice.domain.pay.payinfo.Payment
import hanghae.four.taxiservice.domain.taxi.call.CallReader
import hanghae.four.taxiservice.unit.infrastructures.call.FakeCallRepository
import hanghae.four.taxiservice.unit.infrastructures.client.FakeClientRepository
import hanghae.four.taxiservice.unit.infrastructures.pay.FakePaymentHistoryStore
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class PaymentServiceTest {

    private lateinit var paymentService: PaymentService
    private lateinit var fakeClientRepository: FakeClientRepository
    private lateinit var callReader: CallReader
    private lateinit var paymentHistoryStore: PaymentHistoryStore

    @BeforeEach
    fun setup() {
        fakeClientRepository = FakeClientRepository()
        callReader = FakeCallRepository()
        paymentHistoryStore = FakePaymentHistoryStore()

        paymentService = PaymentService(fakeClientRepository, callReader, paymentHistoryStore)

        fakeClientRepository.store(Client())
    }

    @Test
    fun `택시 현금 결제 성공`() {
        val request = PaymentCommand(
            clientId = 1L,
            callId = 1L,
            paymentId = null,
            amount = BigDecimal(1000),
            payType = Payment.Type.CASH
        )

        val payId = paymentService.pay(request)

        assertThat(payId).isEqualTo(1L)
    }

    @Test
    fun `결제시 결제할 회원이 존재하지 않다면 에러`() {
        val request = PaymentCommand(
            clientId = 2L,
            callId = 1L,
            paymentId = null,
            amount = BigDecimal(1000),
            payType = Payment.Type.CASH
        )

        Assertions.assertThatThrownBy { paymentService.pay(request) }
            .isInstanceOf(java.lang.IllegalArgumentException::class.java)
    }
}
