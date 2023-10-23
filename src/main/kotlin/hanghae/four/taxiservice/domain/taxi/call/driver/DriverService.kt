package hanghae.four.taxiservice.domain.taxi.call.driver

import org.springframework.stereotype.Service

@Service
class DriverService(
    private val driverReader: DriverReader,
) {
    fun getDriver(driverId: Long): DriverInfo {
        val driver = driverReader.getDriver(driverId)
        return DriverInfo(requireNotNull(driver.id))
    }
}
