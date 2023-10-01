package hanghae.four.taxiservice.applications.taxi.call

import hanghae.four.taxiservice.domain.taxi.call.CallCommand
import hanghae.four.taxiservice.domain.taxi.call.CallResult
import hanghae.four.taxiservice.domain.taxi.call.CallService
import hanghae.four.taxiservice.interfaces.taxi.call.DriverInfo
import hanghae.four.taxiservice.interfaces.taxi.call.TaxiInfo
import hanghae.four.taxiservice.utils.annotations.Facade

@Facade
class CallFacade(
    val callService: CallService,
){
    fun call(callCommand: CallCommand): CallResult {
        val res = callService.call(callCommand)
        return CallResult(
            status = res.status,
            driverInfo = DriverInfo(name = "tester driver"),
            taxiInfo = TaxiInfo(taxiType = "NORMAL", taxiNumber = "123가1234"),
        )
    }
}