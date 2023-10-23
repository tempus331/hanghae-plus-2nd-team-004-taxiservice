package hanghae.four.taxiservice.interfaces.taxi.call

data class CallRequest(
    val origin: String,
    val destination: String,
    val type: String,
    val userId: Long,
)

data class CallResponse(
    val callNumber: String,
    val driverData: DriverData,
    val taxiData: TaxiData,
)

data class DriverData(
    val name: String,
    val phoneNumber: String,
)

data class TaxiData(
    val taxiNumber: Int,
)

data class CallDetailResponse(
    val origin: String,
    val destination: String,
    val fare: Int,
)
