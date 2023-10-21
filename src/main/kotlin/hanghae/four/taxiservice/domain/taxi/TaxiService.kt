package hanghae.four.taxiservice.domain.taxi

import org.springframework.stereotype.Service
import javax.persistence.EntityExistsException

@Service
class TaxiService(
    private val taxiStore: TaxiStore,
    private val taxiReader: TaxiReader,
) {
    fun register(command: RegisterTaxi): Long {
        if (taxiReader.existsBy(command.number)) {
            throw EntityExistsException("중복된 택시 번호가 있습니다.")
        }

        val taxi = taxiStore.store(command.toEntity())
        return requireNotNull(taxi.id)
    }
}
