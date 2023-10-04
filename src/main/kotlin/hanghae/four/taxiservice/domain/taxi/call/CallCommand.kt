package hanghae.four.taxiservice.domain.taxi.call

data class CallCommand(
    val origin: String,
    val destination: String,
    val type: String,
    val userId: Long,
)
