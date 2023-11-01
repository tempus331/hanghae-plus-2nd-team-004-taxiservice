package hanghae.four.taxiservice.infrastructure.taxi.call

import hanghae.four.taxiservice.domain.taxi.call.Call
import hanghae.four.taxiservice.infrastructure.taxi.call.exception.CallNotFoundException
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull

fun CallRepository.getBy(id: Long) = findByIdOrNull(id) ?: throw CallNotFoundException()

interface CallRepository : JpaRepository<Call, Long> {
    fun findByStatus(status: Call.CallStatus): List<Call>
    fun findByCallNumber(callNumber: String): Call?
}
