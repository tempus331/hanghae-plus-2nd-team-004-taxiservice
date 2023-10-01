package hanghae.four.taxiservice.interfaces.taxi.call

import hanghae.four.taxiservice.applications.taxi.call.CallFacade
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CallApiController(
    private val callFacade: CallFacade,
    private val callMapper: CallApiMapper,
) {

    @PostMapping("/api/v1/call")
    fun callTaxi(@RequestBody callRequest: CallRequest): ResponseEntity<CallResponse> {

        val callResult = callFacade.call(callMapper.mapToCallCommand(callRequest))
        val res = callMapper.mapToResponse(callResult)

        val mockResponse = CallResponse(
            driverInfo = DriverInfo(name = "tester driver"),
            taxiInfo = TaxiInfo(taxiType = "NORMAL", taxiNumber = "123가1234"),
        )

        return ResponseEntity.ok(mockResponse)
    }
}