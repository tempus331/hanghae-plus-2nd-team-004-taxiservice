package hanghae.four.taxiservice.domain.taxi.call

interface CallReader {
    fun getById(callId: Long): Call
}
