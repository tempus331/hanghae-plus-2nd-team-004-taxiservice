package hanghae.four.taxiservice.unit.interfaces.taxi

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import hanghae.four.taxiservice.applications.taxi.TaxiFacade
import hanghae.four.taxiservice.domain.taxi.Taxi
import hanghae.four.taxiservice.interfaces.taxi.TaxiApiController
import hanghae.four.taxiservice.interfaces.taxi.TaxiDto
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(TaxiApiController::class)
class TaxiApiControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockkBean
    private lateinit var taxiFacade: TaxiFacade

    @Test
    fun `택시 등록`() {
        val request = TaxiDto.RegisterRequest(type = Taxi.Type.NORMAL, 1L, 1234)
        val result = TaxiDto.RegisterResponse(1L)

        every { taxiFacade.register(request) } returns result

        mockMvc.perform(
            post("/taxis")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.taxiId").value(result.taxiId))
    }
}
