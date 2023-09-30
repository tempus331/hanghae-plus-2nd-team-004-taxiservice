package hanghae.four.taxiservice.domain.driver

import org.springframework.stereotype.Service

@Service
class DriverService(
    private val driverReader: DriverReader
) {
    fun getDriver(driverId: Long): DriverInfo.Main {
        val driver = driverReader.getDriver(driverId)
        return DriverInfo.Main(requireNotNull(driver.id))
    }
}
