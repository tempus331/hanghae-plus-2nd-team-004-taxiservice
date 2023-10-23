package hanghae.four.taxiservice.unit

import com.ninjasquad.springmockk.MockkBean
import hanghae.four.taxiservice.application.taxi.call.CallFacade
import hanghae.four.taxiservice.domain.taxi.call.CallResult
import hanghae.four.taxiservice.interfaces.taxi.call.CallApiController
import hanghae.four.taxiservice.interfaces.taxi.call.CallApiMapper
import hanghae.four.taxiservice.interfaces.taxi.call.CallDetailResponse
import hanghae.four.taxiservice.interfaces.taxi.call.CallRequest
import hanghae.four.taxiservice.interfaces.taxi.call.CallResponse
import hanghae.four.taxiservice.interfaces.taxi.call.DriverData
import hanghae.four.taxiservice.interfaces.taxi.call.TaxiData
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@WebMvcTest(controllers = [CallApiController::class])
class CallApiControllerUnitTest : AbstractRestControllerUnitTest() {

    @MockkBean
    private lateinit var callFacade: CallFacade

    @MockkBean
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

        every { callApiMapper.mapToCallCommand(any()) } returns mockk()
        every { callApiMapper.mapToResponse(any()) } returns mockResponseFixture

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

    @Test
    fun `택시 기사는 배정된 Call의 상세 정보를 요창하면 200을 응답한다`() {
        val callResponse = CallDetailResponse(
            origin = "서울시 강남구",
            destination = "서울시 강북구",
            fare = 10000
        )

        every { callApiMapper.mapToCallDetailResponse(any()) } returns callResponse
        every { callFacade.getCallDetailInfo(any()) } returns mockk()

        mockMvc.get("/api/v1/call/1") {
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { json(objectMapper.writeValueAsString(callResponse)) }
        }
    }
}
