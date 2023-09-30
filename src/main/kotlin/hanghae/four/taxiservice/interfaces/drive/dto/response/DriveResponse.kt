package hanghae.four.taxiservice.interfaces.drive.dto.response

data class DriveResponse(
    val startPoint: String,
    val destination: String,
    val fare: Int,
)
