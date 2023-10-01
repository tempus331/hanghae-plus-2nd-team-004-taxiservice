package hanghae.four.taxiservice.interfaces.taxi.call

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CallApiController {


    @PostMapping("/api/v1/call")
    fun callTaxi(): ResponseEntity<*> {
        val mockResponse = CallResponse(
            driverInfo = DriverInfo(name = "tester driver"),
            taxiInfo = TaxiInfo(taxiType = "NORMAL", taxiNumber = "123가1234"),
        )

        return ResponseEntity.ok(mockResponse)
    }
}