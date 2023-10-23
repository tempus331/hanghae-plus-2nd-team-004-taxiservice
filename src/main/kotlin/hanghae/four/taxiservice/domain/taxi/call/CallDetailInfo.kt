package hanghae.four.taxiservice.domain.taxi.call

import hanghae.four.taxiservice.interfaces.taxi.call.CallDetailResponse

fun CallDetailInfo.toResponse(): CallDetailResponse = CallDetailResponse(
    origin = this.origin,
    destination = this.destination,
    fare = this.fare
)

data class CallDetailInfo(
    val origin: String,
    val destination: String,
    val fare: Int,
)
