package hanghae.four.taxiservice.unit.domain.driver

import hanghae.four.taxiservice.domain.taxi.call.driver.DriverCommand
import hanghae.four.taxiservice.domain.taxi.call.driver.DriverReader
import hanghae.four.taxiservice.domain.taxi.call.driver.DriverService
import hanghae.four.taxiservice.domain.taxi.call.driver.DriverStore
import hanghae.four.taxiservice.unit.infrastructures.driver.FakeDriverRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class DriverServiceTest {
    private lateinit var driverService: DriverService

    private lateinit var driverReader: DriverReader

    private lateinit var driverStore: DriverStore

    @BeforeEach
    fun setup() {
        driverReader = FakeDriverRepository()
        driverStore = driverReader as FakeDriverRepository
        driverService = DriverService(driverReader, driverStore)
    }

    @Test
    fun `택시기사 id로 조회 성공`() {
        val driver = driverService.getDriver(1L)

        assertThat(driver.id).isEqualTo(1L)
    }

    @Test
    fun `택시 기사 등록 성공`() {
        val driverId = driverService.register(
            DriverCommand.RegisterDriver(
                name = "기사1",
                licenseNumber = "12345678901234567890",
                phoneNumber = "010-1234-5678"
            )
        )

        assertThat(driverId).isEqualTo(1L)
    }
}
