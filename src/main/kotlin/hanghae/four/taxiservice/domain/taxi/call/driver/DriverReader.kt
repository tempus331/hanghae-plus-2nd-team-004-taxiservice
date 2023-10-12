package hanghae.four.taxiservice.domain.taxi.call.driver

interface DriverReader {
    fun getDriver(driverId: Long): Driver
}
