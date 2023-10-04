package hanghae.four.taxiservice.domain.taxi.call.dispatch

import hanghae.four.taxiservice.domain.taxi.call.CallResult

data class DispatchResult(
    val callNumber: String,
    val diverName: String,
    val taxiNumber: Int,
    val driverPhoneNumber: String,
)

fun DispatchResult.toCallResult(): CallResult {
    return CallResult(
        callNumber = this.callNumber,
        taxiNumber = this.taxiNumber,
        driverName = this.diverName,
        driverPhoneNumber = this.driverPhoneNumber
    )
}
