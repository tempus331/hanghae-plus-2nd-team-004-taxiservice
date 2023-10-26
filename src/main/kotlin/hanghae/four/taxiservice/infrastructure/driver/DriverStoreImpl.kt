package hanghae.four.taxiservice.infrastructure.driver

import hanghae.four.taxiservice.domain.taxi.call.driver.Driver
import hanghae.four.taxiservice.domain.taxi.call.driver.DriverStore
import hanghae.four.taxiservice.global.annotations.Store

@Store
class DriverStoreImpl(
    private val driverRepository: DriverRepository,
) : DriverStore {
    override fun register(driver: Driver): Driver {
        TODO("Not yet implemented")
    }
}
