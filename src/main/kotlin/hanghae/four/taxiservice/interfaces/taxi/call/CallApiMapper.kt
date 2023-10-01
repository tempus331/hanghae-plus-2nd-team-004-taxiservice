package hanghae.four.taxiservice.interfaces.taxi.call

import hanghae.four.taxiservice.domain.taxi.call.CallCommand
import hanghae.four.taxiservice.domain.taxi.call.CallResult
import org.springframework.stereotype.Component

@Component
class CallApiMapper {

    fun mapToCallCommand(callRequest: CallRequest): CallCommand {
        return CallCommand(
            origin = callRequest.origin,
            destination = callRequest.destination,
            type = callRequest.type,
            userId = callRequest.userId,
        )
    }

    fun mapToResponse(callResult: CallResult): Any {
        return CallResponse(
            driverInfo = DriverInfo(name = "tester driver"),
            taxiInfo = TaxiInfo(taxiType = "NORMAL", taxiNumber = "123가1234"),
        )
    }
}