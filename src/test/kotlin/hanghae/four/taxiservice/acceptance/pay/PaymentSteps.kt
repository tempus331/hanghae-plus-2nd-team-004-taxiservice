package hanghae.four.taxiservice.acceptance.pay

import hanghae.four.taxiservice.acceptance.AcceptanceTestSteps
import hanghae.four.taxiservice.domain.pay.Payment
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import org.springframework.http.MediaType
import java.math.BigDecimal

class PaymentSteps : AcceptanceTestSteps() {

    companion object {
        fun `택시 요금 결제`(
            clientId: Long,
            callId: Long,
            amount: BigDecimal,
            payType: Payment.Type,
            pgType: Payment.Type?,
        ): ExtractableResponse<Response> {
            val params: MutableMap<String, Any?> = mutableMapOf()
            params.put("clientId", clientId)
            params.put("callId", callId)
            params.put("amount", amount)
            params.put("payType", payType)
            params.put("pgType", pgType)

            return given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(params)
                .`when`().post("/api/v1/pay")
                .then().log().all().extract()
        }
    }
}