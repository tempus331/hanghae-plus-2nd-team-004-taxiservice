package hanghae.four.taxiservice.integrations.bookmark

import hanghae.four.taxiservice.integrations.AbstractIntegrationTests
import hanghae.four.taxiservice.interfaces.bookmark.RegisterRequest
import org.hamcrest.Matchers
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.post

@SpringBootTest
class BookmarkApiIntegrationTests : AbstractIntegrationTests() {

    @Test
    fun `위치를 즐겨찾기로 등록한다`() {
        val mockRequest = RegisterRequest(
            clientId = 100L,
            country = "",
            city = "",
            placeName = "",
            latitude = 37.501952,
            longitude = 127.044529,
        )

        mockMvc.post("/api/v1/bookmark/register") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(mockRequest)
        }.andExpect {
            status { isOk() }
            jsonPath("$.locationId", Matchers.notNullValue())
            jsonPath("$.clientId", equalTo(100)) // clientId가 1인지 검증
        }
    }
}
