package hanghae.four.taxiservice.acceptance.taxi

import hanghae.four.taxiservice.acceptance.AcceptanceTest
import hanghae.four.taxiservice.acceptance.driver.DriverSteps
import hanghae.four.taxiservice.domain.taxi.Taxi
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

class TaxiAcceptanceTest : AcceptanceTest() {

    @Test
    fun `택시 등록을 한다`() {
        DriverSteps.`기사 등록`("기사1", "12345678901234567890", "010-1234-5678")

        val response = TaxiSteps.`택시 등록`(Taxi.Type.NORMAL, 1L, 1234)

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value())
        assertThat(response.jsonPath().getLong("taxiId")).isEqualTo(1L)
    }

    @Test
    fun `택시 상태를 WAITING 으로 변경 한다`() {
        DriverSteps.`기사 등록`("기사1", "12345678901234567890", "010-1234-5678")

        val taxiResponse = TaxiSteps.`택시 등록`(Taxi.Type.NORMAL, 1L, 1234)
        val taxiId = taxiResponse.jsonPath().getLong("taxiId")

        val response = TaxiSteps.`택시 WAITING 상태 변경`(taxiId)

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value())
        assertThat(response.jsonPath().getLong("taxiId")).isEqualTo(1L)
        assertThat(response.jsonPath().getString("status")).isEqualTo(Taxi.Status.WAITING.name)
    }
}
