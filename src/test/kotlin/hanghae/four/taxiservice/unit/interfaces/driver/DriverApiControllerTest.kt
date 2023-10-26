package hanghae.four.taxiservice.unit.interfaces.driver

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import hanghae.four.taxiservice.application.driver.DriverFacade
import hanghae.four.taxiservice.interfaces.driver.DriverApiController
import hanghae.four.taxiservice.interfaces.driver.DriverDto
import hanghae.four.taxiservice.interfaces.taxi.RegisterRequest
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(DriverApiController::class)
class DriverApiControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockkBean
    private lateinit var driverFacade: DriverFacade

    private val driverId = 1L

    @Test
    fun `택시 기사 등록`() {
        val request = DriverDto.RegisterRequest(
            name = "기사1",
            licenseNumber = "12345678901234567890",
            phoneNumber = "010-1234-5678"
        )

        every { driverFacade.register(request.toDriverCommand()) } returns driverId

        mockMvc.perform(
            post("/api/v1/drivers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andDo(print())
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.driverId").value(driverId))
    }

    @Test
    fun `택시 등록시 택시 종류가 null 입력하면 에러`() {
        val request = RegisterRequest(type = null, 1L, 1234)

        mockMvc.perform(
            post("/api/v1/drivers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andDo(print())
            .andExpect(status().isBadRequest)
    }
}
