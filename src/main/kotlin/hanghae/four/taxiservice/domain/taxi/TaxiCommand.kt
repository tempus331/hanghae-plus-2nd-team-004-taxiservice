package hanghae.four.taxiservice.domain.taxi

data class RegisterTaxi(
    val driverId: Long,
    val type: Taxi.Type,
    val number: Int,
) {
    fun toEntity(): Taxi {
        return Taxi(
            driverId = driverId,
            type = type,
            number = number,
            status = Taxi.Status.CLOSED
        )
    }
}
