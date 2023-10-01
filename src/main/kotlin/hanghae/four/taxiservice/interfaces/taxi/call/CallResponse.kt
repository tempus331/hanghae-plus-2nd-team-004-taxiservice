package hanghae.four.taxiservice.interfaces.taxi.call

data class CallResponse(
    val driverInfo: DriverInfo,
    val taxiInfo: TaxiInfo,
)

data class DriverInfo(
    val name: String,
)

data class TaxiInfo(
    val taxiNumber: String,
    val taxiType: String,
)