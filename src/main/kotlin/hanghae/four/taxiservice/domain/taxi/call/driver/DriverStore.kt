package hanghae.four.taxiservice.domain.taxi.call.driver

interface DriverStore {
    fun register(driver: Driver): Driver
}
