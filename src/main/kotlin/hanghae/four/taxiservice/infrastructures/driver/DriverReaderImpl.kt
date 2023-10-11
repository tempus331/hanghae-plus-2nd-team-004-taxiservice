package hanghae.four.taxiservice.infrastructures.driver

import hanghae.four.taxiservice.domain.taxi.call.driver.Driver
import hanghae.four.taxiservice.domain.taxi.call.driver.DriverReader
import hanghae.four.taxiservice.infrastructures.util.findByIdOrThrow
import org.springframework.stereotype.Repository

@Repository
class DriverReaderImpl(
    private val driverRepository: DriverRepository,
) : DriverReader {

    override fun getDriver(driverId: Long): Driver {
        return driverRepository.findByIdOrThrow(driverId)
    }
}
