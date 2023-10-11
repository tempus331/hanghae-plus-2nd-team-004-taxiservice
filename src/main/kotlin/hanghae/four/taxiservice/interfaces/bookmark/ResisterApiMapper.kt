package hanghae.four.taxiservice.interfaces.bookmark

import hanghae.four.taxiservice.domain.bookmark.BookmarkCommand
import hanghae.four.taxiservice.domain.bookmark.BookmarkResisterResult
import org.springframework.stereotype.Component

@Component
class ResisterApiMapper {

    fun mapToCommand(resisterRequest: ResisterRequest): BookmarkCommand {
        return BookmarkCommand(
            client = resisterRequest.clientId,
            country = resisterRequest.country,
            city = resisterRequest.city,
            placeName = resisterRequest.placeName,
            latitude = resisterRequest.latitude,
            longitude = resisterRequest.longitude,
        )
    }

    fun mapToResponse(bookmarkResisterResult: BookmarkResisterResult): ResisterResponse {
        return ResisterResponse(
            clientId = bookmarkResisterResult.clientId,
            locationId = bookmarkResisterResult.locationId,
        )
    }
}
