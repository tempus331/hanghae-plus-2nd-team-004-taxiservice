package hanghae.four.taxiservice.domain.taxi.call.dispatch

import hanghae.four.taxiservice.domain.taxi.Taxi
import hanghae.four.taxiservice.domain.taxi.call.CallCommand

interface TaxiDispatcher {
    fun supports(callType: String): Boolean
    fun dispatch(taxis: List<Taxi>, callCommand: CallCommand): DispatchResult
}