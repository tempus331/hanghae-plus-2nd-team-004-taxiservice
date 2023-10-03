package hanghae.four.taxiservice.unit.infrastructures.driver

import hanghae.four.taxiservice.domain.driver.Driver
import hanghae.four.taxiservice.domain.driver.DriverReader

class FakeDriverRepository : DriverReader {
    override fun getDriver(driverId: Long): Driver {
        val driver = Driver(1L, "기사1", "12345678901234567890", "010-1234-5678")
        if (driver.id != driverId) {
            throw IllegalArgumentException()
        }

        return driver
    }
}
