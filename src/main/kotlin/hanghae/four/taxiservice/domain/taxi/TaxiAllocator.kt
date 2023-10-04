package hanghae.four.taxiservice.domain.taxi

import hanghae.four.taxiservice.domain.taxi.call.CallCommand
import hanghae.four.taxiservice.domain.taxi.call.dispatch.DispatchResult
import hanghae.four.taxiservice.domain.taxi.call.dispatch.TaxiDispatcher
import org.springframework.stereotype.Component

@Component
class TaxiAllocator(
    private val dispatchers: List<TaxiDispatcher>,
) {
    fun execute(taxis: List<Taxi>, callCommand: CallCommand): DispatchResult =
        findDispatcher().dispatch(taxis, callCommand)

    private fun findDispatcher(callType: String = "RANDOM"): TaxiDispatcher =
        dispatchers.find { it.supports(callType) }
            ?: throw IllegalArgumentException("지원 하지 않는 호출 타입입니다. callType: $callType")
}
