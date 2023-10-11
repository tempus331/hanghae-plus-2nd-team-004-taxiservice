package hanghae.four.taxiservice.infrastructures.taxi.call

import hanghae.four.taxiservice.domain.taxi.call.driver.DriverReader
import hanghae.four.taxiservice.domain.taxi.Taxi
import hanghae.four.taxiservice.domain.taxi.TaxiStore
import hanghae.four.taxiservice.domain.taxi.call.Call
import hanghae.four.taxiservice.domain.taxi.call.CallCommand
import hanghae.four.taxiservice.domain.taxi.call.CallStore
import hanghae.four.taxiservice.domain.taxi.call.dispatch.DispatchResult
import hanghae.four.taxiservice.domain.taxi.call.dispatch.TaxiDispatcher
import hanghae.four.taxiservice.infrastructures.taxi.call.exception.NotExistsCallableTaxiException
import org.springframework.stereotype.Component

@Component
class RandomDispatcher(
    private val taxiStore: TaxiStore,
    private val callStore: CallStore,
    private val driverReader: DriverReader,
) : TaxiDispatcher {

    override fun supports(callType: String): Boolean = callType == "RANDOM"

    /**
     * 택시를 랜덤하게 선택한 뒤 택시가 승낙하면 출발 상태로 변경한다.
     *
     * Flow
     *  1. 택시 선택을 한다.
     *  2. Call Entity가 생성된다.
     *  3. 택시에게 승낙 요청 (call card)를 보낸다.
     *  4. 승낙하면 call은 매칭 상태로 변경된다. 거절하면 매칭 실패 상태로 변경된다.
     *      -> retry 로직으로 인해 N개의 승낙이 생길 수 있기에 이를 커버해야 한다.
     *      -> 분산 시스템이라 가정하고 Redis를 사용한다.
     *  5.
     */
    override fun dispatch(taxis: List<Taxi>, callCommand: CallCommand): DispatchResult {
        if (taxis.isEmpty()) {
            throw NotExistsCallableTaxiException()
        }

        val selectTaxi = taxis.random()
        val storedTaxi = taxiStore.store(selectTaxi.run { this.departToCustomer() })
        val driver = driverReader.getDriver(storedTaxi.driverId)

        val storedCall = callStore.store(
            Call(
                userId = callCommand.userId,
                taxiId = selectTaxi.id!!,
                origin = callCommand.origin,
                destination = callCommand.destination
            ).apply { this.accept() }
        )

        // todo: 택시에게 Call Card 전송 구현은 현재는 생략
//        if (! sendCall(storedCall, callCommand))
//            throw IllegalArgumentException("택시 Call Rejected Taxi Id: . ${selectTaxi.id}")

        return DispatchResult(
            diverName = driver.name,
            driverPhoneNumber = driver.phoneNumber,
            taxiNumber = storedTaxi.number,
            callNumber = storedCall.callNumber
        )
    }
}
