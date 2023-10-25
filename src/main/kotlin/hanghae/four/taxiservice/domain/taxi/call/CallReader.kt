package hanghae.four.taxiservice.domain.taxi.call

interface CallReader {
    fun getById(callId: Long): Call
    fun findCall(callId: Long): Call

    fun findCallingClients(): List<Call>
}
