package hanghae.four.taxiservice.domain.taxi

import org.springframework.stereotype.Component

@Component
class TaxiResearcher(val taxiReader: TaxiReader) {
    /**
     * 사용자가 요청한 Type에 맞는 현재 주행 가능한 택시를 찾는다.
     *
     * 현재는 거리는 고려하지 않고, Type으로만 조회
     */
    fun searchCurrentUsableTaxis(type: String): Any {

    }
}