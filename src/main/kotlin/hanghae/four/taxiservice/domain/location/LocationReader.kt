package hanghae.four.taxiservice.domain.location

interface LocationReader {
    fun getLocation(locationId: Long): Location
}
