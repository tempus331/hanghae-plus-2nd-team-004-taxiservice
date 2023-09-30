package hanghae.four.taxiservice.domain.taxi

interface TaxiStore {
    fun store(taxi: Taxi): Taxi
}
