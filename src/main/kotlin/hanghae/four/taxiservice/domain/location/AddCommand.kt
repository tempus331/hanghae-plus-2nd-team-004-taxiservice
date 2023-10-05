package hanghae.four.taxiservice.domain.location

data class AddCommand (
    val client: Long,
    val country: String,
    val city: String,
    val placeName: String,
    val latitude: Double,
    val longitude: Double,
)
