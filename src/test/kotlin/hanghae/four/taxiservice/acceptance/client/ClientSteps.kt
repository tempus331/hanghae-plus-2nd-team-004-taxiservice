package hanghae.four.taxiservice.acceptance.client

import hanghae.four.taxiservice.acceptance.AcceptanceTestSteps
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import org.springframework.http.MediaType

class ClientSteps : AcceptanceTestSteps() {
    companion object {
        fun `사용자 등록`(): ExtractableResponse<Response> {
            return given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .`when`().post("/api/v1/clients")
                .then().log().all().extract()
        }
    }
}
