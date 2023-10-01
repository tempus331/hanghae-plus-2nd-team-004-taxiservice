package hanghae.four.taxiservice.infrastructures.taxi

import hanghae.four.taxiservice.domain.taxi.TaxiReader
import org.springframework.stereotype.Repository

@Repository
class TaxiReaderImpl(
    private val taxiRepository: TaxiRepository
) : TaxiReader {

    override fun existsBy(number: Int): Boolean {
        return taxiRepository.existsByNumber(number)
    }
}
