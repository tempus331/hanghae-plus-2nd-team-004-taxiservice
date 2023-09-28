package hanghae.four.taxiservice.acceptance.taxi

import hanghae.four.taxiservice.acceptance.AcceptanceTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

class TaxiAcceptanceTest : AcceptanceTest() {

    @Test
    fun `택시 등록을 한다`() {
        val response = TaxiSteps.`택시 등록`()

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value())
    }
}