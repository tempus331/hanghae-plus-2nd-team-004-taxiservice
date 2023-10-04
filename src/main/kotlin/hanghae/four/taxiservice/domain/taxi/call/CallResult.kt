package hanghae.four.taxiservice.domain.taxi.call

import hanghae.four.taxiservice.interfaces.taxi.call.DriverData
import hanghae.four.taxiservice.interfaces.taxi.call.TaxiData

data class CallResult(
    val callNumber: String,
    val taxiNumber: Int,
    val driverName: String,
    val driverPhoneNumber: String,
)

fun CallResult.toDriverData(): DriverData {
    return DriverData(
        name = this.driverName,
        phoneNumber = this.driverPhoneNumber
    )
}

fun CallResult.toTaxiData(): TaxiData {
    return TaxiData(
        taxiNumber = this.taxiNumber
    )
}
