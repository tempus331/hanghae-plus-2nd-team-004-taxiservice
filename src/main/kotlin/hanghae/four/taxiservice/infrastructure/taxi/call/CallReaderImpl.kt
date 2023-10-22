package hanghae.four.taxiservice.infrastructure.taxi.call

import hanghae.four.taxiservice.domain.taxi.call.Call
import hanghae.four.taxiservice.domain.taxi.call.CallReader
import hanghae.four.taxiservice.util.annotations.Reader

@Reader
class CallReaderImpl(
    private val callRepository: CallRepository,
) : CallReader {
    override fun getById(callId: Long): Call {
        return callRepository.getBy(callId)
    }

    override fun findCall(callId: Long): Call {
        return callRepository.getBy(callId)
    }
}
