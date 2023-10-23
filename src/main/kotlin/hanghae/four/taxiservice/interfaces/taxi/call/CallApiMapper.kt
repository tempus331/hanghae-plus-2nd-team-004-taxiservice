package hanghae.four.taxiservice.interfaces.taxi.call

import hanghae.four.taxiservice.domain.taxi.Taxi
import hanghae.four.taxiservice.domain.taxi.call.CallCommand
import hanghae.four.taxiservice.domain.taxi.call.CallDetailInfo
import hanghae.four.taxiservice.domain.taxi.call.CallResult
import hanghae.four.taxiservice.domain.taxi.call.toDriverData
import hanghae.four.taxiservice.domain.taxi.call.toResponse
import hanghae.four.taxiservice.domain.taxi.call.toTaxiData
import org.springframework.stereotype.Component

@Component
class CallApiMapper {

    fun mapToCallCommand(callRequest: CallRequest): CallCommand {
        Taxi.Type.valueOf(callRequest.type.uppercase())

        return CallCommand(
            origin = callRequest.origin,
            destination = callRequest.destination,
            type = callRequest.type,
            userId = callRequest.userId
        )
    }

    fun mapToResponse(callResult: CallResult): CallResponse {
        return CallResponse(
            callNumber = callResult.callNumber,
            driverData = callResult.toDriverData(),
            taxiData = callResult.toTaxiData()
        )
    }

    fun mapToCallDetailResponse(detailInfo: CallDetailInfo): CallDetailResponse? {
        return detailInfo.toResponse()
    }
}
