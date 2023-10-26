package hanghae.four.taxiservice.acceptance.driver

import hanghae.four.taxiservice.acceptance.AcceptanceTestSteps
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import org.springframework.http.MediaType

class DriverSteps : AcceptanceTestSteps() {
    companion object {
        fun `기사 등록`(
            name: String,
            licenseNumber: String,
            phoneNumber: String,
        ): ExtractableResponse<Response> {
            val params: MutableMap<String, Any> = mutableMapOf()
            params.put("name", name)
            params.put("licenseNumber", licenseNumber)
            params.put("phoneNumber", phoneNumber)

            return given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(params)
                .`when`().post("/api/v1/drivers")
                .then().log().all().extract()
        }
    }
}
