package hanghae.four.taxiservice.acceptance.call

import hanghae.four.taxiservice.acceptance.AcceptanceTestSteps
import hanghae.four.taxiservice.domain.taxi.Taxi
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import org.springframework.http.MediaType

class CallSteps : AcceptanceTestSteps() {

    companion object {
        fun `택시 호출`(
            origin: String,
            destination: String,
            type: Taxi.Type,
            userId: Long,
        ): ExtractableResponse<Response> {
            val params: MutableMap<String, Any> = mutableMapOf()
            params.put("origin", origin)
            params.put("destination", destination)
            params.put("type", type)
            params.put("userId", userId)

            return given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(params)
                .`when`().post("/api/v1/call")
                .then().log().all().extract()
        }
    }
}
