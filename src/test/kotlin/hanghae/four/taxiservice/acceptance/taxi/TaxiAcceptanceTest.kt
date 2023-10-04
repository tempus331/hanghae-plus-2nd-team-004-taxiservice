package hanghae.four.taxiservice.acceptance.taxi

import hanghae.four.taxiservice.acceptance.AcceptanceTest
import hanghae.four.taxiservice.domain.driver.Driver
import hanghae.four.taxiservice.domain.taxi.Taxi
import hanghae.four.taxiservice.infrastructures.driver.DriverRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus

class TaxiAcceptanceTest : AcceptanceTest() {

    @Autowired
    private lateinit var driverRepository: DriverRepository

    @Test
    fun `택시 등록을 한다`() {
        // 택시기사 등록 임시 추가, 나중에 수정해야함
        driverRepository.save(
            Driver(name = "기사1", phoneNumber = "010-1234-5678", licenseNumber = "12345678901234567890")
        )

        val response = TaxiSteps.`택시 등록`(Taxi.Type.NORMAL, 1L, 1234)

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value())
        assertThat(response.jsonPath().getLong("taxiId")).isEqualTo(1L)
    }
}
