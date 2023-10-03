package hanghae.four.taxiservice.acceptance.pay

import hanghae.four.taxiservice.acceptance.AcceptanceTest
import hanghae.four.taxiservice.acceptance.taxi.TaxiSteps
import hanghae.four.taxiservice.domain.client.Client
import hanghae.four.taxiservice.domain.driver.Driver
import hanghae.four.taxiservice.domain.pay.Payment
import hanghae.four.taxiservice.domain.taxi.Taxi
import hanghae.four.taxiservice.infrastructures.client.ClientRepository
import hanghae.four.taxiservice.infrastructures.driver.DriverRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import java.math.BigDecimal

class PaymentAcceptanceTest : AcceptanceTest() {

    @Autowired
    private lateinit var driverRepository: DriverRepository

    @Autowired
    private lateinit var clientRepository: ClientRepository

    @Test
    fun `택시 결제하기`() {
        // 기사/사용자 등록 -> 택시등록 -> 택시 호출 -> 결제
        val client = clientRepository.save(Client())

        val driver = driverRepository.save(Driver())

        val taxiResponse = TaxiSteps.`택시 등록`(Taxi.Type.NORMAL, requireNotNull(driver.id), 1234)
        val taxiId = taxiResponse.jsonPath().getLong("taxiId")

        val response = PaymentSteps.`택시 요금 현금 결제`(requireNotNull(client.id), taxiId, BigDecimal(1000), Payment.Type.CASH, null)

        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value())
        Assertions.assertThat(response.jsonPath().getLong("paymentId")).isEqualTo(1L)
    }
}
