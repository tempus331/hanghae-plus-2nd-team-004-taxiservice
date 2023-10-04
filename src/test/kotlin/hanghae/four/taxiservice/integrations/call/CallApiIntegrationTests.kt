package hanghae.four.taxiservice.integrations.call

import hanghae.four.taxiservice.domain.taxi.call.Call
import hanghae.four.taxiservice.infrastructures.taxi.call.CallRepository
import hanghae.four.taxiservice.integrations.AbstractIntegrationTests
import hanghae.four.taxiservice.interfaces.taxi.call.CallRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
class CallApiIntegrationTests(
    @Autowired
    private val callRepository: CallRepository,
) : AbstractIntegrationTests() {

    @Test
    fun `유효한 데이터로 배차 요청을 진행하면 200응답을 반환한다`() {
        val mockRequest = CallRequest(
            type = "NORMAL",
            origin = "서울시 강남구",
            destination = "서울시 강북구",
            userId = 1L
        )

        mockMvc.post("/api/v1/call") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(mockRequest)
        }.andExpect {
            status { isOk() }
        }

        val taxis = callRepository.findAll()
        assertThat(taxis.size).isEqualTo(1)
        assertThat(taxis[0].status).isEqualTo(Call.CallStatus.RUNNING)
    }

    @Test
    fun `배차 확정 된 기사가 배차 정보를 호출하면 200을 반환한다`() {
        // 배차 저장
        callRepository.save(
            Call(
                userId = 1L,
                origin = "서울시 강남구",
                destination = "서울시 강북구",
                status = Call.CallStatus.RUNNING,
                taxiId = 1L
            )
        )

        mockMvc.get("/api/v1/call/{callId}", 1L) {
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            jsonPath("$.origin") { value("서울시 강남구") }
            jsonPath("$.fare") { value("10000") }
        }
    }
}
