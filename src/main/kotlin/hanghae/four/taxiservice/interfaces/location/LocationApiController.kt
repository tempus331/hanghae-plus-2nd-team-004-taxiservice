package hanghae.four.taxiservice.interfaces.location

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LocationApiController {

    @PostMapping("/api/v1/location/add")
    fun addLocation(): ResponseEntity<*> {
        val mockResponse = AddResponse(
            clientId = 1L,
            locationId = 1L,
        )

        return ResponseEntity.ok(mockResponse)
    }
}
