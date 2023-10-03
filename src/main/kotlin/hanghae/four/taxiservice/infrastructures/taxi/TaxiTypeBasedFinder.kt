package hanghae.four.taxiservice.infrastructures.taxi

import hanghae.four.taxiservice.domain.taxi.Taxi
import hanghae.four.taxiservice.domain.taxi.TaxiFinder
import hanghae.four.taxiservice.domain.taxi.TaxiReader
import org.springframework.stereotype.Component

@Component
class TaxiTypeBasedFinder(
    private val taxiReader: TaxiReader
) : TaxiFinder {

    override fun findUsableTaxis(type: String): List<Taxi> =
        taxiReader.findAllNotRunningTaxisByType(type)

}