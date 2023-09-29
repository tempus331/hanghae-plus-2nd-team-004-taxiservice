package hanghae.four.taxiservice.domain.taxi

import org.springframework.stereotype.Service

@Service
class TaxiService(
    private val taxiStore: TaxiStore
) {
    fun register(request: TaxiCommand.RegisterTaxi): Long {
        val taxi = request.toEntity()

        val saveTaxi = taxiStore.store(taxi)
        return requireNotNull(saveTaxi.id)
    }
}