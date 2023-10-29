package hanghae.four.taxiservice.domain.taxi

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
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

    fun findTaxi(taxiId: Long): Taxi {
        return taxiReader.findTaxi(taxiId)
    }

    @Transactional
    fun updateTaxiWait(taxiId: Long): TaxiResult.TaxiResponse {
        val taxi = taxiReader.getTaxi(taxiId)
        taxiStore.store(taxi.run { this.updateWaiting() })

        return TaxiResult.TaxiResponse(
            taxiId = requireNotNull(taxi.id),
            type = taxi.type,
            number = taxi.number,
            status = taxi.status
        )
    }

    @Transactional
    fun updateTaxiCheckIn(taxiId: Long): TaxiResult.TaxiResponse {
        val taxi = taxiReader.getTaxi(taxiId)
        taxiStore.store(taxi.run { this.checkIn() })

        return TaxiResult.TaxiResponse(
            taxiId = requireNotNull(taxi.id),
            type = taxi.type,
            number = taxi.number,
            status = taxi.status
        )
    }

    fun runningComplete(taxi: Taxi) {
        taxiStore.store(taxi.run { this.runningComplete() })
    }
}
