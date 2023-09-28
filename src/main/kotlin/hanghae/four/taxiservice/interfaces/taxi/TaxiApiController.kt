package hanghae.four.taxiservice.interfaces.taxi

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class TaxiApiController {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/taxis")
    fun register() {

    }
}