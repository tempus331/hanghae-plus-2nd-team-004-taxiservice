package hanghae.four.taxiservice.integrations.call

import hanghae.four.taxiservice.integrations.AbstractIntegrationTests
import hanghae.four.taxiservice.interfaces.taxi.call.CallRequest
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.post

@SpringBootTest
class CallApiTests : AbstractIntegrationTests() {

    @Test
    fun `유효한 데이터로 배차 요청을 진행하면 200응답을 반환한다`() {

        val mockRequest = CallRequest(
            type = "NORMAL",
            origin = "서울시 강남구",
            destination = "서울시 강북구",
            userId = 1L,
        )

        mockMvc.post("/api/v1/call") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(mockRequest)
        }.andExpect {
            status { isOk() }
        }
    }
}