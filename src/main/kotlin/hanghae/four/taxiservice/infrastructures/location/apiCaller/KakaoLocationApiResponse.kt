package hanghae.four.taxiservice.infrastructures.location.apiCaller

import hanghae.four.taxiservice.domain.location.locationCaller.Location
import hanghae.four.taxiservice.domain.location.locationCaller.LocationCoordinates

data class KakaoLocationApiResponse(
    val documents: List<Document>,
)

data class Document(
    val road_address: RoadAddress,
    val address: Address,
)

data class Address(
    val address_name: String,
)

data class RoadAddress(
    val address_name: String,
    val building_name: String,
)

fun kakaoLocationApiResponseToLocation(
    coordinates: LocationCoordinates,
    kakaoLocationApiResponse: KakaoLocationApiResponse,
): Location {
    val documents: List<Document> = kakaoLocationApiResponse.documents
    return if (documents.isNotEmpty()) {
        Location(
            addressName = kakaoLocationApiResponse.documents[0].address.address_name,
            roadAddressName = kakaoLocationApiResponse.documents[0].road_address.address_name,
            name = kakaoLocationApiResponse.documents[0].road_address.building_name,
            latitude = coordinates.latitude,
            longitude = coordinates.longitude
        )
    } else {
        throw IllegalArgumentException("주소를 확인할 수 없는 좌표값입니다.")
    }
}
