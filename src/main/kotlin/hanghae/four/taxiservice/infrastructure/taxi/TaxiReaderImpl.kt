package hanghae.four.taxiservice.infrastructure.taxi

import hanghae.four.taxiservice.domain.taxi.Taxi
import hanghae.four.taxiservice.domain.taxi.TaxiReader
import hanghae.four.taxiservice.infrastructure.util.findByIdOrThrow
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

    override fun getTaxi(taxiId: Long): Taxi {
        return taxiRepository.findByIdOrThrow(taxiId)
    }

    override fun findTaxi(taxiId: Long): Taxi {
        return taxiRepository.findByIdOrThrow(taxiId)
    }
}
