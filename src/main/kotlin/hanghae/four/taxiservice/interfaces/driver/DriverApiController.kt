package hanghae.four.taxiservice.interfaces.driver

import hanghae.four.taxiservice.application.driver.DriverFacade
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class DriverApiController(
    private val driverFacade: DriverFacade,
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/drivers")
    fun register(
        @Valid @RequestBody
        request: DriverDto.RegisterRequest,
    ): DriverDto.RegisterResponse {
        val driverId = driverFacade.register(request.toDriverCommand())
        return DriverDto.RegisterResponse(driverId)
    }
}
