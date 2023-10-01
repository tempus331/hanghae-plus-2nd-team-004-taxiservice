package hanghae.four.taxiservice.domain.taxi.call

import hanghae.four.taxiservice.interfaces.taxi.call.DriverInfo
import hanghae.four.taxiservice.interfaces.taxi.call.TaxiInfo

data class CallResult(
    val status: Boolean,
    val driverInfo: DriverInfo?,
    val taxiInfo: TaxiInfo?,
)
