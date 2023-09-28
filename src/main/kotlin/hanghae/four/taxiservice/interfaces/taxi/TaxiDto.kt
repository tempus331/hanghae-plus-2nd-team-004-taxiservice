package hanghae.four.taxiservice.interfaces.taxi

import hanghae.four.taxiservice.domain.taxi.Taxi

class TaxiDto {

    data class RegisterRequest(
        val type: Taxi.Type,
        val driver: Long,
        val number: Int
    ) {}

    data class RegisterResponse(
        val taxiId: Long
    ) {}
}