package hanghae.four.taxiservice.domain.drive

import hanghae.four.taxiservice.interfaces.drive.dto.response.DriveResponse

data class DriveInfo(
    val startPoint: String,
    val destination: String,
    val fare: Int,
)

fun DriveInfo.toResponse() = DriveResponse(
    startPoint = this.startPoint,
    destination = this.destination,
    fare = this.fare,
)