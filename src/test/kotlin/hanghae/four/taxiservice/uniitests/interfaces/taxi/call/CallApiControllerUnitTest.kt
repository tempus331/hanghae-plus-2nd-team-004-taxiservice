package hanghae.four.taxiservice.uniitests.interfaces.taxi.call

import hanghae.four.taxiservice.applications.taxi.call.CallFacade
import hanghae.four.taxiservice.interfaces.taxi.call.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.post

@WebMvcTest(controllers = [CallApiController::class])
class CallApiControllerUnitTest: AbstractRestControllerUnitTest() {

    @MockBean
    private lateinit var callFacade: CallFacade

    @MockBean
    private lateinit var callApiMapper: CallApiMapper

    @Test
    fun `택시 호출을 하면 200을 응답한다`() {
        val CALL_REQUEST_FIXTURE = CallRequest(
            taxiId = 1L,
            userId = 1L,
        )

        val mockResponse = CallResponse(
            driverInfo = DriverInfo(name = "tester driver"),
            taxiInfo = TaxiInfo(taxiType = "NORMAL", taxiNumber = "123가1234"),
        )

        mockMvc.post("/api/v1/call") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(CALL_REQUEST_FIXTURE)
        }.andExpect {
            status { isOk() }
            content {
                json(objectMapper.writeValueAsString(mockResponse))
            }
        }
    }
}