package hanghae.four.taxiservice.interfaces.bookmark

data class RegisterRequest(
    val clientId: Long,
    val country: String,
    val city: String,
    val placeName: String,
    val latitude: Double,
    val longitude: Double,
)
