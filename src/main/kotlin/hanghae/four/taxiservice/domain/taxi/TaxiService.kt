package hanghae.four.taxiservice.domain.taxi

import hanghae.four.taxiservice.interfaces.taxi.TaxiDto
import org.springframework.stereotype.Service

@Service
class TaxiService(
    private val taxiStore: TaxiStore
) {
    fun register(request: TaxiDto.RegisterRequest): Long {
        TODO("Not yet implemented")
    }
}