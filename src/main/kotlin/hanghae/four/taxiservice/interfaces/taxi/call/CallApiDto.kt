package hanghae.four.taxiservice.interfaces.taxi.call

data class DriverInfo(
    val name: String,
)

data class TaxiInfo(
    val taxiNumber: String,
    val taxiType: String,
)

data class CallRequest(
    val origin: String,
    val destination: String,
    val type: String,
    val userId: Long,
)

data class CallResponse(
    val driverInfo: DriverInfo,
    val taxiInfo: TaxiInfo,
)

