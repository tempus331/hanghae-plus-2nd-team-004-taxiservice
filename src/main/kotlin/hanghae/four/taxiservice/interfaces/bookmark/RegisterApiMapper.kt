package hanghae.four.taxiservice.interfaces.bookmark

import hanghae.four.taxiservice.domain.bookmark.BookmarkCommand
import hanghae.four.taxiservice.domain.bookmark.BookmarkRegisterResult
import org.springframework.stereotype.Component

@Component
class RegisterApiMapper {

    fun mapToCommand(registerRequest: RegisterRequest): BookmarkCommand {
        return BookmarkCommand(
            client = registerRequest.clientId,
            country = registerRequest.country,
            city = registerRequest.city,
            placeName = registerRequest.placeName,
            latitude = registerRequest.latitude,
            longitude = registerRequest.longitude
        )
    }

    fun mapToResponse(bookmarkResisterResult: BookmarkRegisterResult): RegisterResponse {
        return RegisterResponse(
            clientId = bookmarkResisterResult.clientId,
            locationId = bookmarkResisterResult.locationId
        )
    }
}
