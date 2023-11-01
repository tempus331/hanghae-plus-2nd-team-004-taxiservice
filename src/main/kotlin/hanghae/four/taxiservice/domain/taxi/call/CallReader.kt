package hanghae.four.taxiservice.domain.taxi.call

interface CallReader {
    fun getById(callId: Long): Call
    fun findCall(callNumber: String): Call

    fun findCallingClients(): List<Call>
}
