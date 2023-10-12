package hanghae.four.taxiservice.domain.taxi.call

import hanghae.four.taxiservice.domain.taxi.TaxiAllocator
import hanghae.four.taxiservice.domain.taxi.TaxiFinder
import hanghae.four.taxiservice.domain.taxi.call.dispatch.toCallResult
import hanghae.four.taxiservice.infrastructures.taxi.call.exception.NotExistsCallableTaxiException
import org.springframework.retry.annotation.Backoff
import org.springframework.retry.annotation.Recover
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Service

@Service
class CallService(
    val taxiFinder: TaxiFinder,
    val taxiAllocator: TaxiAllocator,
    val callReader: CallReader,
    val fareCalculator: FareCalculator,
) {

    @Retryable(
        value = [NotExistsCallableTaxiException::class],
        maxAttempts = 3,
        backoff = Backoff(delay = 3000L)
    )
    fun call(callCommand: CallCommand): CallResult {
        // todo: locking 조회 시점엔 택시가 running 상태가 아니어도 최종 시점엔 running 상태일 수 있음
        val usableTaxis = taxiFinder.findUsableTaxis(callCommand.type)
        return taxiAllocator.execute(usableTaxis, callCommand).toCallResult()
    }

    fun getCallDetailInfo(callId: Long): CallDetailInfo = with(callReader.getById(callId)) {
        // todo: user 정보는 추후 구현
        CallDetailInfo(
            origin = this.origin,
            destination = this.destination,
            fare = fareCalculator.calculate(origin, destination)
        )
    }

    @Recover
    fun recover(): CallResult? {
        throw NotExistsCallableTaxiException()
        return null
    }
}
