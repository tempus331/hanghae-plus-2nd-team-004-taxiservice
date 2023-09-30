package hanghae.four.taxiservice.domain.taxi

import org.springframework.stereotype.Service
import javax.persistence.EntityExistsException

@Service
class TaxiService(
    private val taxiStore: TaxiStore,
    private val taxiReader: TaxiReader
) {
    fun register(request: TaxiCommand.RegisterTaxi): Long {
        if (taxiReader.existsBy(request.number)) {
            throw EntityExistsException("중복된 택시 번호가 있습니다.")
        }

        val taxi = request.toEntity()

        val saveTaxi = taxiStore.store(taxi)
        return requireNotNull(saveTaxi.id)
    }
}
