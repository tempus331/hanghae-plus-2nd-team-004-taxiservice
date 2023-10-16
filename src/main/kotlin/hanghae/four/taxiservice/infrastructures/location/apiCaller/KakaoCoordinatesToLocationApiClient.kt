package hanghae.four.taxiservice.infrastructures.location.apiCaller

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "coordinates-to-location-api-url", url = "\${kakao-api.coordinates-to-location-api-url}")
interface KakaoCoordinatesToLocationApiClient {

    @GetMapping
    fun getCoordinateToAddress(
        @RequestParam("x") latitude: Double,
        @RequestParam("y") longitude: Double,
        @RequestHeader("Authorization") authorization: String,
    ): KakaoLocationApiResponse
}
