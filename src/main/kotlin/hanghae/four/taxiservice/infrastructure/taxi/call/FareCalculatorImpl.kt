package hanghae.four.taxiservice.infrastructure.taxi.call

import hanghae.four.taxiservice.domain.taxi.call.FareCalculator
import org.springframework.stereotype.Component

@Component
class FareCalculatorImpl : FareCalculator {
    override fun calculate(origin: String, destination: String): Int {
        // 구체적인 요금 구현 로직은 생략
        return 10000
    }
}
