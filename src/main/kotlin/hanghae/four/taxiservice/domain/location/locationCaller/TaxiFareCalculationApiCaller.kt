package hanghae.four.taxiservice.domain.location.locationCaller

interface TaxiFareCalculationApiCaller {
    fun getCalculatedTaxFare(originCoordinates: LocationCoordinates, destinationCoordinates: LocationCoordinates): Long
}
