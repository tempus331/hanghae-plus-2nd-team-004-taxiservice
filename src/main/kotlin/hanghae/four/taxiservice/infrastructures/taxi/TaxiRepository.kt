package hanghae.four.taxiservice.infrastructures.taxi

import hanghae.four.taxiservice.domain.taxi.Taxi
import org.springframework.data.jpa.repository.JpaRepository

interface TaxiRepository : JpaRepository<Taxi, Long> {
    fun existsByNumber(number: Int): Boolean
    fun findAllByTypeAndStatusIs(type: Taxi.Type, status: Taxi.Status): List<Taxi>
}
