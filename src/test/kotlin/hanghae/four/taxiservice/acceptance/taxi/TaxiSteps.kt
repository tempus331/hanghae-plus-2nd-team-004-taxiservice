package hanghae.four.taxiservice.acceptance.taxi

import hanghae.four.taxiservice.acceptance.AcceptanceTestSteps
import hanghae.four.taxiservice.domain.taxi.Taxi
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import org.springframework.http.MediaType

class TaxiSteps : AcceptanceTestSteps() {

    companion object {
        fun `택시 등록`(
            type: Taxi.Type,
            driver: Long,
            number: Int,
        ): ExtractableResponse<Response> {
            val params: MutableMap<String, Any> = mutableMapOf()
            params.put("type", type)
            params.put("driver", driver)
            params.put("number", number)

            return given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(params)
                .`when`().post("/api/v1/taxis")
                .then().log().all().extract()
        }

        fun `택시 WAITING 상태 변경`(
            taxiId: Long,
        ): ExtractableResponse<Response> {
            return given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .`when`().put("/api/v1/taxis/{taxiId}/wait", taxiId)
                .then().log().all().extract()
        }

        fun `택시 RUNNING 상태 변경`(
            taxiId: Long,
        ): ExtractableResponse<Response> {
            return given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .`when`().put("/api/v1/taxis/{taxiId}/check-in", taxiId)
                .then().log().all().extract()
        }
    }
}
