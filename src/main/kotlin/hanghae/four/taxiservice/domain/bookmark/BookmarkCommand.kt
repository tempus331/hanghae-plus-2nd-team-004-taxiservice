package hanghae.four.taxiservice.domain.bookmark

data class BookmarkCommand(
    val client: Long,
    val country: String,
    val city: String,
    val placeName: String,
    val latitude: Double,
    val longitude: Double,
)
