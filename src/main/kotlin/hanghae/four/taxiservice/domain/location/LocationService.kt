package hanghae.four.taxiservice.domain.location

import hanghae.four.taxiservice.domain.location.locationCaller.Location
import hanghae.four.taxiservice.domain.location.locationCaller.LocationApiCaller
import hanghae.four.taxiservice.domain.location.locationCaller.LocationCoordinates
import hanghae.four.taxiservice.domain.location.locationCaller.TaxiFareCalculationApiCaller
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class LocationService(
    private val locationApiCaller: LocationApiCaller,
    private val taxiFareCalculationApiCaller: TaxiFareCalculationApiCaller,
) {

    fun getCurrentLocationByCoordinates(coordinates: LocationCoordinates): Location {
        return locationApiCaller.getCurrentLocationByCoordinates(coordinates)
    }

    fun getCurrentLocationByKeyword(keyword: String): Location {
        return locationApiCaller.getCurrentLocationByKeyword(keyword)
    }

    fun getCalculatedTaxFare(
        originCoordinates: LocationCoordinates,
        destinationCoordinates: LocationCoordinates,
    ): Long {
        return taxiFareCalculationApiCaller.getCalculatedTaxFare(originCoordinates, destinationCoordinates)
    }
}
