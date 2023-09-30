package hanghae.four.taxiservice.domain.driver

import org.springframework.stereotype.Service

@Service
class DriverService(
    private val driverReader: DriverReader
) {
    fun getDriver(driverId: Long): DriverInfo.Main {
        return DriverInfo.Main(id = 1L)
    }
}