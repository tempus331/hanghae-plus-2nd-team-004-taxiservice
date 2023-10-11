package hanghae.four.taxiservice.domain.taxi.call

interface FareCalculator {
    fun calculate(origin: String, destination: String): Int
}
