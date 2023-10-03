package hanghae.four.taxiservice.infrastructures.taxi.call


import hanghae.four.taxiservice.domain.taxi.call.Call
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull


interface CallRepository: JpaRepository<Call, Long> {
}