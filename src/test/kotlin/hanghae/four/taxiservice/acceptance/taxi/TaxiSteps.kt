package hanghae.four.taxiservice.acceptance.taxi

import hanghae.four.taxiservice.acceptance.AcceptanceTestSteps
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import org.springframework.http.MediaType

class TaxiSteps : AcceptanceTestSteps() {

    companion object {
        fun `택시 등록`(): ExtractableResponse<Response> {
            val params: MutableMap<String, Object> = mutableMapOf()

            return given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(params)
                .`when`().post("/taxis")
                .then().log().all().extract()
        }
    }
}