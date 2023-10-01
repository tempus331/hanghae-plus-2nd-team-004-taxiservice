package hanghae.four.taxiservice.domain.taxi

import org.springframework.stereotype.Component

@Component
interface TaxiReader {
    fun getAllNotRunningTaxiAndType(type: String): List<Taxi>
}