package hanghae.four.taxiservice.domain.taxi

import org.springframework.stereotype.Repository

@Repository
interface TaxiReader {
    fun existsBy(number: Int): Boolean
    fun findAllNotRunningTaxisByType(type: String): List<Taxi>
    fun getTaxi(taxiId: Long): Taxi
    fun findTaxi(taxiId: Long): Taxi
}
