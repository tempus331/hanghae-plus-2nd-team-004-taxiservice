package hanghae.four.taxiservice.domain.location

import hanghae.four.taxiservice.domain.location.locationCaller.Location
import hanghae.four.taxiservice.domain.location.locationCaller.LocationApiCaller
import hanghae.four.taxiservice.domain.location.locationCaller.LocationCoordinates
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.random.Random

@Service
@Transactional
class LocationService(
    private val locationApiCaller: LocationApiCaller,
) {
    fun add(addCommand: AddCommand): AddResult {
        // 저장 부분 추가 예정
        // 임시로 저장된 location id 생성
        val locationId = Random.nextLong(1_000_000_000, 10_000_000_000)
        return AddResult(
            locationId = locationId,
            clientId = addCommand.client
        )
    }
    fun getCurrentLocationByCoordinates(coordinates: LocationCoordinates): Location {
        return locationApiCaller.getCurrentLocationByCoordinates(coordinates)
    }

    fun getCurrentLocationByKeyword(keyword: String): Location {
        return locationApiCaller.getCurrentLocationByKeyword(keyword)
    }
}
