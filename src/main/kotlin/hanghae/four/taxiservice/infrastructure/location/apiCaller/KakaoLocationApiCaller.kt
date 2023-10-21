package hanghae.four.taxiservice.unit.infrastructures.location.apiCaller

import hanghae.four.taxiservice.domain.location.locationCaller.Location
import hanghae.four.taxiservice.domain.location.locationCaller.LocationApiCaller
import hanghae.four.taxiservice.domain.location.locationCaller.LocationCoordinates
import org.springframework.stereotype.Component

@Component
class KakaoLocationApiCaller : LocationApiCaller {
    override fun getCurrentLocationByCoordinates(coordinates: LocationCoordinates): Location {
        TODO("Not yet implemented")
    }

    override fun getCurrentLocationByKeyword(keyword: String): Location {
        TODO("Not yet implemented")
    }
}
