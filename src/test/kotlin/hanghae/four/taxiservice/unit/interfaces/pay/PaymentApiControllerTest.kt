package hanghae.four.taxiservice.unit.interfaces.pay

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import hanghae.four.taxiservice.applications.pay.PaymentFacade
import hanghae.four.taxiservice.domain.pay.Payment
import hanghae.four.taxiservice.interfaces.pay.PayRequest
import hanghae.four.taxiservice.interfaces.pay.PaymentApiController
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.math.BigDecimal

@WebMvcTest(PaymentApiController::class)
class PaymentApiControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockkBean
    private lateinit var paymentFacade: PaymentFacade

    @Test
    fun `택시 현금 결제 성공`() {
        val request = PayRequest(
            clientId = 1L,
            callId = 1L,
            amount = BigDecimal(1000),
            payType = Payment.Type.CASH,
            pgType = null
        )

        val paymentId = 1L

        every { paymentFacade.pay(request.toPayCommand()) } returns paymentId

        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/v1/pay")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.paymentId").value(paymentId))
    }
}
