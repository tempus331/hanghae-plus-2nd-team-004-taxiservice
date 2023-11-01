package hanghae.four.taxiservice.infrastructure.taxi.call

import hanghae.four.taxiservice.domain.taxi.call.Call
import hanghae.four.taxiservice.domain.taxi.call.CallReader
import hanghae.four.taxiservice.global.annotations.Reader
import hanghae.four.taxiservice.infrastructure.util.fail

@Reader
class CallReaderImpl(
    private val callRepository: CallRepository,
) : CallReader {
    override fun getById(callId: Long): Call {
        return callRepository.getBy(callId)
    }

    override fun findCall(callNumber: String): Call {
        return callRepository.findByCallNumber(callNumber) ?: fail()
    }

    override fun findCallingClients(): List<Call> {
        return callRepository.findByStatus(Call.CallStatus.WAITING)
    }
}
