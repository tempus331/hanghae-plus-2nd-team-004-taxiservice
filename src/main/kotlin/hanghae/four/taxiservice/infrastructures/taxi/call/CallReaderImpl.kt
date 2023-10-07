package hanghae.four.taxiservice.infrastructures.taxi.call

import hanghae.four.taxiservice.domain.taxi.call.Call
import hanghae.four.taxiservice.domain.taxi.call.CallReader
import hanghae.four.taxiservice.infrastructures.util.fail
import hanghae.four.taxiservice.infrastructures.util.findByIdOrThrow
import hanghae.four.taxiservice.utils.annotations.Reader

@Reader
class CallReaderImpl(
    private val callRepository: CallRepository,
) : CallReader {

    override fun getBy(callId: Long): Call {
        return callRepository.findByIdOrThrow(callId) ?: fail()
    }
}
