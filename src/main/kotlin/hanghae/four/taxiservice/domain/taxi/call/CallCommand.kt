package hanghae.four.taxiservice.domain.taxi.call

import org.springframework.stereotype.Component

data class CallCommand(
    val origin: String,
    val destination: String,
    val type: String,
    val userId: Long,
)
