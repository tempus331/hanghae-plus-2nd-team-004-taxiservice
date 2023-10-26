package hanghae.four.taxiservice.domain.taxi.call.driver

import org.springframework.stereotype.Service

@Service
class DriverService(
    private val driverReader: DriverReader,
    private val driverStore: DriverStore,
) {
    fun getDriver(driverId: Long): DriverInfo {
        val driver = driverReader.getDriver(driverId)
        return DriverInfo(requireNotNull(driver.id))
    }

    fun register(command: DriverCommand.RegisterDriver): Long {
        val driver = driverStore.register(command.toEntity())
        return requireNotNull(driver.id)
    }
}
