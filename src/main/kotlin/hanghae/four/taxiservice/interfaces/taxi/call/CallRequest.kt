package hanghae.four.taxiservice.interfaces.taxi.call

data class CallRequest(
    val taxiId: Long,
    val userId: Long,
)