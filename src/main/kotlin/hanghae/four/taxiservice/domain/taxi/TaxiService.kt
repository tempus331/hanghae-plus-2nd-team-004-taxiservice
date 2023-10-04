package hanghae.four.taxiservice.domain.taxi

import hanghae.four.taxiservice.domain.driver.DriverReader
import org.springframework.stereotype.Service
import javax.persistence.EntityExistsException

@Service
class TaxiService(
    private val driverReader: DriverReader,
    private val taxiStore: TaxiStore,
    private val taxiReader: TaxiReader,
) {
    fun register(request: RegisterTaxi): Long {
        driverReader.getDriver(request.driverId)

        if (taxiReader.existsBy(request.number)) {
            throw EntityExistsException("중복된 택시 번호가 있습니다.")
        }

        val taxi = taxiStore.store(request.toEntity())
        return requireNotNull(taxi.id)
    }
}
