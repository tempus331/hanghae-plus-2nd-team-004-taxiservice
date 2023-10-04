package hanghae.four.taxiservice.domain.location

import org.springframework.stereotype.Service

@Service
class LocationService (
    private val locationReader: LocationReader,
){
    fun getLocation(locationId: Long): LocationInfo {
        val location = locationReader.getLocation(locationId)
        return LocationInfo(requireNotNull(location.id))
    }
}
