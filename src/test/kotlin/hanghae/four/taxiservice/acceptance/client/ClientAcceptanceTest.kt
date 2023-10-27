package hanghae.four.taxiservice.acceptance.client

import hanghae.four.taxiservice.acceptance.AcceptanceTest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

class ClientAcceptanceTest : AcceptanceTest() {
    @Test
    fun `사용자 등록을 한다`() {
        val response = ClientSteps.`사용자 등록`()

        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value())
        Assertions.assertThat(response.jsonPath().getLong("clientId")).isEqualTo(1L)
    }
}
