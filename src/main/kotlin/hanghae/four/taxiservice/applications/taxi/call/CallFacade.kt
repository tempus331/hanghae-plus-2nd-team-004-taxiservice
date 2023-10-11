package hanghae.four.taxiservice.applications.taxi.call

import hanghae.four.taxiservice.domain.taxi.call.CallCommand
import hanghae.four.taxiservice.domain.taxi.call.CallDetailInfo
import hanghae.four.taxiservice.domain.taxi.call.CallResult
import hanghae.four.taxiservice.domain.taxi.call.CallService
import hanghae.four.taxiservice.utils.annotations.Facade

@Facade
class CallFacade(
    val callService: CallService,
) {
    fun call(callCommand: CallCommand): CallResult {
        val res = callService.call(callCommand)
        return CallResult(
            callNumber = res.callNumber,
            taxiNumber = res.taxiNumber,
            driverName = res.driverName,
            driverPhoneNumber = res.driverPhoneNumber
        )
    }

    fun getCallDetailInfo(taxiId: Long): CallDetailInfo {
        return callService.getCallDetailInfo(taxiId)
    }
}
