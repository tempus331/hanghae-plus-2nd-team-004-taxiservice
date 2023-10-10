package hanghae.four.taxiservice.unit.domain.pay

import hanghae.four.taxiservice.domain.client.Client
import hanghae.four.taxiservice.domain.pay.PayFactory
import hanghae.four.taxiservice.domain.pay.PaymentCommand
import hanghae.four.taxiservice.domain.pay.PaymentHistoryStore
import hanghae.four.taxiservice.domain.pay.PaymentService
import hanghae.four.taxiservice.domain.pay.payinfo.Payment
import hanghae.four.taxiservice.domain.taxi.Taxi
import hanghae.four.taxiservice.domain.taxi.call.Call
import hanghae.four.taxiservice.unit.infrastructures.call.FakeCallRepository
import hanghae.four.taxiservice.unit.infrastructures.client.FakeClientRepository
import hanghae.four.taxiservice.unit.infrastructures.pay.FakePaymentHistoryRepository
import hanghae.four.taxiservice.unit.infrastructures.pay.FakePaymentRepository
import hanghae.four.taxiservice.unit.infrastructures.taxi.FakeTaxiRepository
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class PaymentServiceTest {

    private lateinit var paymentService: PaymentService
    private lateinit var fakeClientRepository: FakeClientRepository
    private lateinit var fakeCallRepository: FakeCallRepository
    private lateinit var fakePaymentRepository: FakePaymentRepository
    private lateinit var fakeTaxiRepository: FakeTaxiRepository
    private lateinit var paymentHistoryStore: PaymentHistoryStore
    private lateinit var payFactory: PayFactory

    @BeforeEach
    fun setup() {
        fakeClientRepository = FakeClientRepository()
        fakeCallRepository = FakeCallRepository()
        fakePaymentRepository = FakePaymentRepository()
        fakeTaxiRepository = FakeTaxiRepository()
        paymentHistoryStore = FakePaymentHistoryRepository()

        payFactory = mockk()

        paymentService = PaymentService(
            fakeClientRepository,
            fakeCallRepository,
            fakePaymentRepository,
            paymentHistoryStore,
            payFactory
        )

        fakeClientRepository.store(Client())
        fakeTaxiRepository.store(
            Taxi(driverId = 1L, type = Taxi.Type.NORMAL, number = 1234, status = Taxi.Status.RUNNING)
        )
        fakeCallRepository.store(Call(userId = 1L, taxiId = 1L, origin = "서울시 강남구", destination = "서울시 강북구"))
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

        every { payFactory.execute(request.payType) } just Runs

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

    @Test
    fun `결제시 호출된 택시가 존재하지 않는다면 에러`() {
        val request = PaymentCommand(
            clientId = 1L,
            callId = 2L,
            paymentId = null,
            amount = BigDecimal(1000),
            payType = Payment.Type.CASH
        )

        Assertions.assertThatThrownBy { paymentService.pay(request) }
            .isInstanceOf(java.lang.IllegalArgumentException::class.java)
    }

    @Test
    fun `결제시 호출된 택시가 "운행중"이 아니면 에러`() {
        fakeTaxiRepository.store(
            Taxi(driverId = 2L, type = Taxi.Type.NORMAL, number = 1234, status = Taxi.Status.WAITING)
        )

        val request = PaymentCommand(
            clientId = 1L,
            callId = 1L,
            paymentId = null,
            amount = BigDecimal(1000),
            payType = Payment.Type.CASH
        )

        Assertions.assertThatThrownBy { paymentService.pay(request) }
            .isInstanceOf(java.lang.IllegalArgumentException::class.java)
    }

    @Test
    fun `택시 카드 결제 성공`() {
        fakePaymentRepository.store(Payment(clientId = 1L, type = Payment.Type.SAMSUNGCARD))

        val request = PaymentCommand(
            clientId = 1L,
            callId = 1L,
            paymentId = 1L,
            amount = BigDecimal(1000),
            payType = Payment.Type.SAMSUNGCARD
        )

        every { payFactory.execute(request.payType) } just Runs

        val payId = paymentService.pay(request)

        assertThat(payId).isEqualTo(1L)
    }
}
