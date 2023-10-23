package hanghae.four.taxiservice.domain.location.locationCaller

interface LocationApiCaller {
    fun getCurrentLocationByCoordinates(coordinates: LocationCoordinates): Location
    fun getCurrentLocationByKeyword(keyword: String): Location
}
