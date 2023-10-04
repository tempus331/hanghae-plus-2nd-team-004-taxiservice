package hanghae.four.taxiservice.infrastructures.taxi.call

import hanghae.four.taxiservice.domain.taxi.call.Call
import org.springframework.data.jpa.repository.JpaRepository

interface CallRepository : JpaRepository<Call, Long>
