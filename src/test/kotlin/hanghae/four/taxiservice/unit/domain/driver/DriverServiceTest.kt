package hanghae.four.taxiservice.unit.domain.driver

import hanghae.four.taxiservice.domain.taxi.call.driver.DriverReader
import hanghae.four.taxiservice.domain.taxi.call.driver.DriverService
import hanghae.four.taxiservice.unit.infrastructures.driver.FakeDriverRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class DriverServiceTest {
    private lateinit var driverService: DriverService

    private lateinit var driverReader: DriverReader

    @BeforeEach
    fun setup() {
        driverReader = FakeDriverRepository()
        driverService = DriverService(driverReader)
    }

    @Test
    fun `택시기사 id로 조회 성공`() {
        val driver = driverService.getDriver(1L)

        assertThat(driver.id).isEqualTo(1L)
    }
}
