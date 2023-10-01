package hanghae.four.taxiservice.integrations

import hanghae.four.taxiservice.interfaces.taxi.call.CallRequest
import hanghae.four.taxiservice.interfaces.taxi.call.CallResponse
import hanghae.four.taxiservice.interfaces.taxi.call.DriverInfo
import hanghae.four.taxiservice.interfaces.taxi.call.TaxiInfo
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.post

@SpringBootTest
class CallApiTests : AbstractIntegrationTests() {

    @Test
    fun `배차 요청을 한다`() {
        val mockRequest = CallRequest(
            taxiId = 1L,
            userId = 1L,
        )

        val mockResponse = CallResponse(
            driverInfo = DriverInfo(name = "tester driver"),
            taxiInfo = TaxiInfo(taxiType = "NORMAL", taxiNumber = "123가1234"),
        )

        mockMvc.post("/api/v1/call", 1L) {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(mockRequest)
        }.andExpect {
            status { isOk() }
            content { json(objectMapper.writeValueAsString(mockResponse)) } }
    }
}