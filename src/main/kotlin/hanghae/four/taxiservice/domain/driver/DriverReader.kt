package hanghae.four.taxiservice.domain.driver

interface DriverReader {
    fun getDriver(driverId: Long): Driver
}