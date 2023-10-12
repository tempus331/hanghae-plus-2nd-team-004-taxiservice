package hanghae.four.taxiservice.unit.domain.location.fakes

import hanghae.four.taxiservice.domain.location.locationCaller.LocationCoordinates
import hanghae.four.taxiservice.domain.location.locationCaller.TaxiFareCalculationApiCaller

class FakeTaxiFareCalculationApiCaller : TaxiFareCalculationApiCaller {
    override fun getCalculatedTaxFare(
        originCoordinates: LocationCoordinates,
        destinationCoordinates: LocationCoordinates,
    ): Long {
        // 좌표 값이 유효하지 않은 경우 실패 시나리오를 시뮬레이션
        coordinatesValidate(originCoordinates)
        coordinatesValidate(destinationCoordinates)

        // 가짜 요금 데이터 반환
        return 15000L
    }

    private fun coordinatesValidate(coordinates: LocationCoordinates) {
        if (coordinates.latitude.isEmpty() || coordinates.latitude.isEmpty() ||
            coordinates.latitude.toDouble() < -90.0 || coordinates.latitude.toDouble() > 90.0 ||
            coordinates.longitude.toDouble() < -180.0 || coordinates.longitude.toDouble() > 180.0
        ) {
            throw IllegalArgumentException("유효한 좌표값이 아닙니다.")
        }
    }
}
