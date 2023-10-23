package hanghae.four.taxiservice.unit.domain.location.fakes

import hanghae.four.taxiservice.domain.location.locationCaller.Location
import hanghae.four.taxiservice.domain.location.locationCaller.LocationApiCaller
import hanghae.four.taxiservice.domain.location.locationCaller.LocationCoordinates

class FakeLocationApiCaller : LocationApiCaller {
    override fun getCurrentLocationByCoordinates(coordinates: LocationCoordinates): Location {
        // 좌표 값이 유효하지 않은 경우 실패 시나리오를 시뮬레이션
        if (coordinates.latitude.isEmpty() || coordinates.latitude.isEmpty() ||
            coordinates.latitude.toDouble() < -90.0 || coordinates.latitude.toDouble() > 90.0 ||
            coordinates.longitude.toDouble() < -180.0 || coordinates.longitude.toDouble() > 180.0
        ) {
            throw IllegalArgumentException("유효한 좌표값이 아닙니다.")
        }

        // 가짜 데이터로 Location 객체를 생성하여 반환
        return Location(
            addressName = "123 Main Street",
            roadAddressName = "Main Street 12-3",
            name = "Sample Location",
            latitude = coordinates.latitude,
            longitude = coordinates.longitude
        )
    }

    override fun getCurrentLocationByKeyword(keyword: String): Location {
        // keyword 값이 없을 때 실패
        if (keyword.isEmpty()) {
            throw IllegalArgumentException("키워드 값을 입력해야 합니다.")
        }

        // 가짜 데이터로 Location 객체를 생성하여 반환
        return Location(
            addressName = "123 Main Street",
            roadAddressName = "Main Street 12-3",
            name = keyword,
            latitude = "37.123",
            longitude = "-122.456"
        )
    }
}
