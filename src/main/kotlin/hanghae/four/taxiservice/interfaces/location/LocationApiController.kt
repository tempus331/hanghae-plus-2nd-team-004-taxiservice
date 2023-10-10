package hanghae.four.taxiservice.interfaces.location

import hanghae.four.taxiservice.domain.location.LocationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LocationApiController(
    private val locationService: LocationService,
    private val addMapper: addApiMapper,
) {

    @PostMapping("/api/v1/location/add")
    fun addLocation(@RequestBody addRequest: AddRequest): ResponseEntity<AddResponse> {
        val addResult = locationService.add(addMapper.mapToAddCommand(addRequest))
        return ResponseEntity.ok(addMapper.mapToResponse(addResult))
    }
}
