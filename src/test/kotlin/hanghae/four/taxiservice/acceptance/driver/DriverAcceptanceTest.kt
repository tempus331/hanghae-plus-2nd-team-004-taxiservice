package hanghae.four.taxiservice.acceptance.driver

import hanghae.four.taxiservice.acceptance.AcceptanceTest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

class DriverAcceptanceTest : AcceptanceTest() {
    @Test
    fun `기사 등록을 한다`() {
        val response = DriverSteps.`기사 등록`("기사1", "12345678901234567890", "010-1234-5678")

        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value())
        Assertions.assertThat(response.jsonPath().getLong("driverId")).isEqualTo(1L)
    }
}
