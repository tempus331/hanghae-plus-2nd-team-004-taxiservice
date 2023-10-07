package hanghae.four.taxiservice.unit.interfaces.taxi

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import hanghae.four.taxiservice.applications.taxi.TaxiFacade
import hanghae.four.taxiservice.domain.taxi.Taxi
import hanghae.four.taxiservice.interfaces.taxi.RegisterRequest
import hanghae.four.taxiservice.interfaces.taxi.TaxiApiController
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

@WebMvcTest(TaxiApiController::class)
class TaxiApiControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockkBean
    private lateinit var taxiFacade: TaxiFacade

    private val taxiId = 1L

    @Test
    fun `택시 등록`() {
        val request = RegisterRequest(type = Taxi.Type.NORMAL, 1L, 1234)

        every { taxiFacade.register(request.toTaxiCommand()) } returns taxiId

        mockMvc.perform(
            post("/api/v1/taxis")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andDo(print())
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.taxiId").value(taxiId))
    }

    @Test
    fun `택시 등록시 택시 종류가 null 입력하면 에러`() {
        val request = RegisterRequest(type = null, 1L, 1234)

        mockMvc.perform(
            post("/api/v1/taxis")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andDo(print())
            .andExpect(status().isBadRequest)
    }
}
