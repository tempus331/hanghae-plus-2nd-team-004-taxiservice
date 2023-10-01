package hanghae.four.taxiservice.domain.taxi.call

import hanghae.four.taxiservice.domain.taxi.TaxiReader
import hanghae.four.taxiservice.domain.taxi.TaxiResearcher
import hanghae.four.taxiservice.interfaces.taxi.call.DriverInfo
import hanghae.four.taxiservice.interfaces.taxi.call.TaxiInfo
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CallService(val taxiReader: TaxiResearcher, val taxiCallValidator: TaxiCallValidator, val taxiAllocator: TaxiAllocator) {

    fun call(callCommand: CallCommand): CallResult {
        // todo: locking 조회 시점엔 택시가 running 상태가 아니어도 최종 시점엔 running 상태일 수 있음
        val taxis = taxiReader.searchCurrentUsableTaxis(callCommand.type)
        // taxiCallValidator.validate(taxi)

        return if (taxiAllocator.allocate(taxis))
            CallResult(
                status = true,
                driverInfo = DriverInfo(name = "tester driver"),
                taxiInfo = TaxiInfo(taxiType = "NORMAL", taxiNumber = "123가1234"),
            )
        else {
            CallResult(
                status = false,
                driverInfo = null,
                taxiInfo = null,
            )
        }
    }
}