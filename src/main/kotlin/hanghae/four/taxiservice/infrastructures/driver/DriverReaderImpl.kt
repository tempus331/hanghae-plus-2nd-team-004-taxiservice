package hanghae.four.taxiservice.infrastructures.driver

import hanghae.four.taxiservice.domain.driver.Driver
import hanghae.four.taxiservice.domain.driver.DriverReader
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
