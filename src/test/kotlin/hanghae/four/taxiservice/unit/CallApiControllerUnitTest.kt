package hanghae.four.taxiservice.unit

import hanghae.four.taxiservice.applications.taxi.call.CallFacade
import hanghae.four.taxiservice.domain.taxi.call.CallResult
import hanghae.four.taxiservice.interfaces.taxi.call.CallApiController
import hanghae.four.taxiservice.interfaces.taxi.call.CallApiMapper
import hanghae.four.taxiservice.interfaces.taxi.call.CallRequest
import hanghae.four.taxiservice.interfaces.taxi.call.CallResponse
import hanghae.four.taxiservice.interfaces.taxi.call.DriverData
import hanghae.four.taxiservice.interfaces.taxi.call.TaxiData
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.post

@WebMvcTest(controllers = [CallApiController::class])
class CallApiControllerUnitTest : AbstractRestControllerUnitTest() {

    private val callFacade: CallFacade = mockk()

    @MockBean
    private lateinit var callApiMapper: CallApiMapper

    @Test
    fun `택시 호출을 하면 200을 응답한다`() {
        val callRequestFixture = CallRequest(
            origin = "서울시 강남구",
            destination = "서울시 강북구",
            type = "NORMAL",
            userId = 1L
        )

        val mockResponseFixture = CallResponse(
            callNumber = "1234",
            driverData = DriverData(
                name = "홍길동",
                phoneNumber = "010-1234-5678"
            ),
            taxiData = TaxiData(
                taxiNumber = 1234
            )
        )

        every { callFacade.call(any()) } returns CallResult(
            callNumber = "1234",
            taxiNumber = 1234,
            driverName = "홍길동",
            driverPhoneNumber = "010-1234-5678"
        )

        mockMvc.post("/api/v1/call") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(callRequestFixture)
        }.andExpect {
            status { isOk() }
            content {
                json(objectMapper.writeValueAsString(mockResponseFixture))
            }
        }
    }
}
