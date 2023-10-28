package hanghae.four.taxiservice.domain.taxi

class TaxiResult {
    data class TaxiResponse(
        val taxiId: Long,
        val type: Taxi.Type,
        val number: Int,
        val status: Taxi.Status,
    )
}
