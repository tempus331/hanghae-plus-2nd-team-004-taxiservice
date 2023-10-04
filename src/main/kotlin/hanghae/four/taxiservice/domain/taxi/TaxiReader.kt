package hanghae.four.taxiservice.domain.taxi

import org.springframework.stereotype.Repository

@Repository
interface TaxiReader {
    fun existsBy(number: Int): Boolean
}
