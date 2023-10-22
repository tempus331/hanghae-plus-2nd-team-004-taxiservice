package hanghae.four.taxiservice.unit.domain.pay

import hanghae.four.taxiservice.domain.pay.PayFactory
import hanghae.four.taxiservice.domain.pay.PaymentCommand
import hanghae.four.taxiservice.domain.pay.PaymentHistoryStore
import hanghae.four.taxiservice.domain.pay.PaymentService
import hanghae.four.taxiservice.domain.pay.payinfo.Payment
import hanghae.four.taxiservice.domain.taxi.Taxi
import hanghae.four.taxiservice.domain.taxi.call.Call
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
    private lateinit var fakePaymentRepository: FakePaymentRepository
    private lateinit var fakeTaxiRepository: FakeTaxiRepository
    private lateinit var paymentHistoryStore: PaymentHistoryStore
    private lateinit var payFactory: PayFactory

    private lateinit var call: Call

    @BeforeEach
    fun setup() {
        fakePaymentRepository = FakePaymentRepository()
        fakeTaxiRepository = FakeTaxiRepository()
        paymentHistoryStore = FakePaymentHistoryRepository()

        payFactory = mockk()

        paymentService = PaymentService(
            fakeTaxiRepository,
            fakePaymentRepository,
            paymentHistoryStore,
            payFactory
        )

        fakeTaxiRepository.store(
            Taxi(driverId = 1L, type = Taxi.Type.NORMAL, number = 1234, status = Taxi.Status.RUNNING)
        )

        call = Call(userId = 1L, taxiId = 1L, origin = "서울시 강남구", destination = "서울시 강북구")
    }

    @Test
    fun `택시 현금 결제 성공`() {
        call.accept()

        val command = PaymentCommand(
            clientId = 1L,
            callId = 1L,
            paymentId = null,
            amount = BigDecimal(1000),
            payType = Payment.Type.CASH
        )

        every { payFactory.execute(command.payType) } just Runs

        val payId = paymentService.pay(command, call)

        assertThat(payId).isEqualTo(1L)
    }

    @Test
    fun `결제시 호출된 택시가 "운행중"이 아니면 에러`() {
        fakeTaxiRepository.store(
            Taxi(driverId = 2L, type = Taxi.Type.NORMAL, number = 1234, status = Taxi.Status.WAITING)
        )

        call = Call(userId = 1L, taxiId = 2L, origin = "서울시 강남구", destination = "서울시 강북구")
        call.accept()

        val command = PaymentCommand(
            clientId = 1L,
            callId = 2L,
            paymentId = null,
            amount = BigDecimal(1000),
            payType = Payment.Type.CASH
        )

        Assertions.assertThatThrownBy { paymentService.pay(command, call) }
            .isInstanceOf(java.lang.IllegalArgumentException::class.java)
    }

    @Test
    fun `호출된 택시가 "RUNNING" 이 아니라면 에러`() {
        val command = PaymentCommand(
            clientId = 1L,
            callId = 1L,
            paymentId = 1L,
            amount = BigDecimal(1000),
            payType = Payment.Type.SAMSUNGCARD
        )

        Assertions.assertThatThrownBy { paymentService.pay(command, call) }
            .isInstanceOf(java.lang.IllegalStateException::class.java)
    }

    @Test
    fun `카드, 페이 결제시 결제 방법이 등록되어 있지 않다면 에러`() {
        call.accept()

        val command = PaymentCommand(
            clientId = 1L,
            callId = 1L,
            paymentId = 1L,
            amount = BigDecimal(1000),
            payType = Payment.Type.SAMSUNGCARD
        )

        Assertions.assertThatThrownBy { paymentService.pay(command, call) }
            .isInstanceOf(java.lang.IllegalArgumentException::class.java)
    }

    @Test
    fun `택시 카드 결제 성공`() {
        call.accept()

        fakePaymentRepository.store(Payment(clientId = 1L, type = Payment.Type.SAMSUNGCARD))

        val command = PaymentCommand(
            clientId = 1L,
            callId = 1L,
            paymentId = 1L,
            amount = BigDecimal(1000),
            payType = Payment.Type.SAMSUNGCARD
        )

        every { payFactory.execute(command.payType) } just Runs

        val payId = paymentService.pay(command, call)

        assertThat(payId).isEqualTo(1L)
    }
}
