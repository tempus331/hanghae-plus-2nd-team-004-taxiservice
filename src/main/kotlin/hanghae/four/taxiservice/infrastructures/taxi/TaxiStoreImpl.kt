package hanghae.four.taxiservice.infrastructures.taxi

import hanghae.four.taxiservice.domain.taxi.Taxi
import hanghae.four.taxiservice.domain.taxi.TaxiStore
import org.springframework.stereotype.Repository

@Repository
class TaxiStoreImpl(
    private val taxiRepository: TaxiRepository
) : TaxiStore {

    override fun store(taxi: Taxi): Taxi {
        return taxiRepository.save(taxi)
    }
}
