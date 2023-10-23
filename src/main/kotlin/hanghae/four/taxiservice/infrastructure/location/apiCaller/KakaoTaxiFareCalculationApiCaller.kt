package hanghae.four.taxiservice.infrastructure.location.apiCaller

import hanghae.four.taxiservice.domain.location.locationCaller.LocationCoordinates
import hanghae.four.taxiservice.domain.location.locationCaller.TaxiFareCalculationApiCaller
import org.springframework.stereotype.Component

@Component
class KakaoTaxiFareCalculationApiCaller : TaxiFareCalculationApiCaller {
    override fun getCalculatedTaxFare(
        originCoordinates: LocationCoordinates,
        destinationCoordinates: LocationCoordinates,
    ): Long {
        TODO("Not yet implemented")
    }
}
