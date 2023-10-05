package hanghae.four.taxiservice.integrations.location

import hanghae.four.taxiservice.integrations.AbstractIntegrationTests
import hanghae.four.taxiservice.interfaces.location.AddRequest
import hanghae.four.taxiservice.interfaces.location.AddResponse
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.post

@SpringBootTest
class LocationApiIntegrationTests : AbstractIntegrationTests() {

    @Test
    fun `위치를 즐겨찾기로 등록한다`() {
        val mockRequest = AddRequest(
            clientId = 1L,
            country = "",
            city = "",
            placeName = "",
            latitude = 37.501952,
            longitude = 127.044529,
        )

        val mockResponse = AddResponse(
            clientId = 1L,
            locationId = 1L,
        )

        mockMvc.post("/api/v1/location/add") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(mockRequest)
        }.andExpect {
            status { isOk() }
            content { json(objectMapper.writeValueAsString(mockResponse))}
        }
    }
}
