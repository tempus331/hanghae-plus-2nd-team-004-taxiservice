package hanghae.four.taxiservice.interfaces.location

import hanghae.four.taxiservice.domain.location.AddCommand
import hanghae.four.taxiservice.domain.location.AddResult
import org.springframework.stereotype.Component

@Component
class addApiMapper {

    fun mapToAddCommand(addRequest: AddRequest): AddCommand {
        return AddCommand(
            client = addRequest.clientId,
            country = addRequest.country,
            city = addRequest.city,
            placeName = addRequest.placeName,
            latitude = addRequest.latitude,
            longitude = addRequest.longitude,
        )
    }

    fun mapToResponse(addResult: AddResult): AddResponse {
        return AddResponse(
            clientId = addResult.clientId,
            locationId = addResult.locationId,
        )
    }
}
