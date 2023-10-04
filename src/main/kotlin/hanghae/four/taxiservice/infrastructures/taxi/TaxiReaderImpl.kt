package hanghae.four.taxiservice.infrastructures.taxi

import hanghae.four.taxiservice.domain.taxi.Taxi
import hanghae.four.taxiservice.domain.taxi.TaxiReader
import org.springframework.stereotype.Repository

@Repository
class TaxiReaderImpl(
    private val taxiRepository: TaxiRepository,
) : TaxiReader {

    override fun existsBy(number: Int): Boolean {
        return taxiRepository.existsByNumber(number)
    }

    override fun findAllNotRunningTaxisByType(type: String): List<Taxi> {
        return taxiRepository.findAllByTypeAndStatusIs(Taxi.Type.NORMAL, Taxi.Status.WAITING)
    }
}
