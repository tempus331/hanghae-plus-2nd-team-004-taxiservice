package hanghae.four.taxiservice.domain.taxi.call

interface CallReader {
    fun getBy(callId: Long): Call
}
