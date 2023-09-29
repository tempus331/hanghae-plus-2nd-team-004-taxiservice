package hanghae.four.taxiservice.interfaces.taxi

import hanghae.four.taxiservice.applications.taxi.TaxiFacade
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class TaxiApiController(
    private val taxiFacade: TaxiFacade
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/taxis")
    fun register(): TaxiDto.RegisterResponse {
        return TaxiDto.RegisterResponse(taxiId = 1L)
    }
}