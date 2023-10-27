package hanghae.four.taxiservice.acceptance.pay

import hanghae.four.taxiservice.acceptance.AcceptanceTest
import hanghae.four.taxiservice.acceptance.client.ClientSteps
import hanghae.four.taxiservice.acceptance.driver.DriverSteps
import hanghae.four.taxiservice.acceptance.taxi.TaxiSteps
import hanghae.four.taxiservice.domain.pay.payinfo.PayInfo
import hanghae.four.taxiservice.domain.taxi.Taxi
import hanghae.four.taxiservice.domain.taxi.call.Call
import hanghae.four.taxiservice.infrastructure.pay.payinfo.PayInfoRepository
import hanghae.four.taxiservice.infrastructure.taxi.call.CallRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import java.math.BigDecimal

class PaymentAcceptanceTest : AcceptanceTest() {

    @Autowired
    private lateinit var callRepository: CallRepository

    @Autowired
    private lateinit var payInfoRepository: PayInfoRepository

    private var clientId: Long = 0
    private lateinit var call: Call

    // 기사/사용자 등록 -> 택시등록 -> 결제 방법 등록 -> 택시 호출 -> 결제
    @BeforeEach
    fun init() {
        val driverResponse = DriverSteps.`기사 등록`("기사1", "12345678901234567890", "010-1234-5678")
        val driverId = driverResponse.jsonPath().getLong("driverId")

        val clientResponse = ClientSteps.`사용자 등록`()
        clientId = clientResponse.jsonPath().getLong("driverId")

        val taxiResponse = TaxiSteps.`택시 등록`(Taxi.Type.NORMAL, requireNotNull(driverId), 1234)
        val taxiId = taxiResponse.jsonPath().getLong("taxiId")

        call = callRepository.save(
            Call(userId = clientId, taxiId = taxiId, origin = "서울시 강남구", destination = "서울시 강북구")
        )
    }

    @Disabled
    @Test
    fun `택시 현금 결제하기`() {
        val response = PaymentSteps.`택시 요금 결제`(
            requireNotNull(clientId),
            requireNotNull(call.id),
            null,
            BigDecimal(1000),
            PayInfo.Type.CASH
        )

        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value())
        Assertions.assertThat(response.jsonPath().getLong("paymentHistoryId")).isEqualTo(1L)
    }

    @Disabled
    @Test
    fun `택시 카드 결제하기`() {
        val payInfo = payInfoRepository.save(PayInfo(requireNotNull(clientId), PayInfo.Type.SAMSUNGCARD))

        val response = PaymentSteps.`택시 요금 결제`(
            requireNotNull(clientId),
            requireNotNull(call.id),
            payInfo.id,
            BigDecimal(1000),
            PayInfo.Type.SAMSUNGCARD
        )

        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value())
        Assertions.assertThat(response.jsonPath().getLong("paymentHistoryId")).isEqualTo(1L)
    }

    @Disabled
    @Test
    fun `택시 페이 결제하기`() {
        val payInfo = payInfoRepository.save(PayInfo(requireNotNull(clientId), PayInfo.Type.NAVERPAY))

        val response = PaymentSteps.`택시 요금 결제`(
            requireNotNull(clientId),
            requireNotNull(call.id),
            payInfo.id,
            BigDecimal(1000),
            PayInfo.Type.NAVERPAY
        )

        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value())
        Assertions.assertThat(response.jsonPath().getLong("paymentHistoryId")).isEqualTo(1L)
    }
}
