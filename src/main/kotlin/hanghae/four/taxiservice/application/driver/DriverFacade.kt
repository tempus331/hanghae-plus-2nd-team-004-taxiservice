package hanghae.four.taxiservice.application.driver

import hanghae.four.taxiservice.domain.taxi.call.driver.DriverCommand
import hanghae.four.taxiservice.domain.taxi.call.driver.DriverService
import hanghae.four.taxiservice.global.annotations.Facade

@Facade
class DriverFacade(
    private val driverService: DriverService,
) {
    fun register(command: DriverCommand.RegisterDriver): Long {
        return driverService.register(command)
    }
}
