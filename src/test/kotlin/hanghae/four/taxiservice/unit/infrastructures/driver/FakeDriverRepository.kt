package hanghae.four.taxiservice.unit.infrastructures.driver

import hanghae.four.taxiservice.domain.driver.Driver
import hanghae.four.taxiservice.domain.driver.DriverReader

class FakeDriverRepository : DriverReader {
    override fun getDriver(driverId: Long): Driver {
        val driver = Driver(1L)
        if (driver.id != driverId) {
            throw IllegalArgumentException()
        }

        return driver
    }
}