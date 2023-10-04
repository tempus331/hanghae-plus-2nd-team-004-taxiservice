package hanghae.four.taxiservice.infrastructures.taxi.call

import hanghae.four.taxiservice.domain.taxi.call.Call
import hanghae.four.taxiservice.domain.taxi.call.CallStore
import hanghae.four.taxiservice.utils.annotations.Store

@Store
class CallStoreImpl(
    private val callRepository: CallRepository,
): CallStore {

    override fun store(call: Call): Call {
        return callRepository.save(call)
    }
}