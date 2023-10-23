package hanghae.four.taxiservice.unit.domain.call

import hanghae.four.taxiservice.domain.taxi.TaxiAllocator
import hanghae.four.taxiservice.domain.taxi.TaxiFinder
import hanghae.four.taxiservice.domain.taxi.call.Call
import hanghae.four.taxiservice.domain.taxi.call.CallCommand
import hanghae.four.taxiservice.domain.taxi.call.CallDetailInfo
import hanghae.four.taxiservice.domain.taxi.call.CallService
import hanghae.four.taxiservice.domain.taxi.call.FareCalculator
import hanghae.four.taxiservice.domain.taxi.call.dispatch.DispatchResult
import hanghae.four.taxiservice.domain.taxi.call.dispatch.toCallResult
import hanghae.four.taxiservice.infrastructure.taxi.call.exception.CallNotFoundException
import hanghae.four.taxiservice.unit.domain.call.fakes.FakeTaxiFinder
import hanghae.four.taxiservice.unit.infrastructures.call.FakeCallRepository
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@DisplayName("Unit: CallService")
@ExtendWith(MockitoExtension::class)
internal class CallServiceTest {

    private val taxiAllocator: TaxiAllocator = mockk()
    private val taxiFinder: TaxiFinder = FakeTaxiFinder()
    private val callRepository = FakeCallRepository()
    private val fareCalculator: FareCalculator = mockk()
    private lateinit var sut: CallService

    @BeforeEach
    fun setUp() {
        sut = CallService(taxiFinder, taxiAllocator, callRepository, fareCalculator)
    }

    @DisplayName("call 메서드는")
    @Nested
    inner class Describe_of_call {

        @DisplayName("유효한 호출 명령을 받아 호출 가능한 택시가 존재할 경우")
        @Nested
        inner class ContextOfValidCallCommand {

            @DisplayName("호출 결과를 반환한다")
            @Test
            fun it_returns_call_result() {
                val callCommand = CallCommand(
                    origin = "서울시 강서구",
                    destination = "서울시 강남구",
                    type = "NORMAL",
                    userId = 1L
                )
                val dispatchResult = DispatchResult(
                    callNumber = "1234",
                    diverName = "홍길동",
                    taxiNumber = 1234,
                    driverPhoneNumber = "010-1234-1234"
                )

                every { taxiAllocator.execute(any(), any()) } returns dispatchResult
                val res = sut.call(callCommand)
                assertThat(res).isEqualTo(dispatchResult.toCallResult())
            }
        }

        @DisplayName("호출 가능한 택시가 존재하지 않을 경우")
        @Nested
        inner class Context_of_not_exists_taxi {

            @DisplayName("택시 호출에 실패한다.")
            @Test
            fun it_throw_IllegalArgumentException() {
                val callCommand = CallCommand(
                    origin = "서울시 강서구",
                    destination = "서울시 강남구",
                    type = "NORMAL",
                    userId = 1L
                )

                every {
                    taxiAllocator.execute(
                        any(),
                        any()
                    )
                } throws IllegalArgumentException("호출 가능한 택시가 없습니다.") // 익셉션 발생
                assertThrows<IllegalArgumentException> { sut.call(callCommand) }
            }
        }
    }

    @DisplayName("getDetailInfo 메서드는")
    @Nested
    inner class Describe_of_getDetailInfo {

        @DisplayName("유효한 Call Id를 받으면,")
        @Nested
        inner class Context_of_valid_call_id {

            @DisplayName("호출 상세 정보를 반환한다")
            @Test
            fun it_returns_call_detail_info() {
                val mockDetailInfo = CallDetailInfo(
                    origin = "서울시 강남구",
                    destination = "서울시 강북구",
                    fare = 10000
                )

                callRepository.store(
                    Call(
                        id = 1L,
                        userId = 1L,
                        callNumber = "1234",
                        origin = "서울시 강남구",
                        destination = "서울시 강북구",
                        status = Call.CallStatus.RUNNING,
                        taxiId = 1L
                    )
                )

                every { fareCalculator.calculate(any(), any()) } returns 10000

                val res = sut.getCallDetailInfo(1L)
                assertThat(res).isEqualTo(mockDetailInfo)
            }
        }

        @DisplayName("존재하지 않는 Call Id를 받으면,")
        @Nested
        inner class Context_of_invalid_call_id {

            @DisplayName("예외가 발생한다.")
            @Test
            fun it_returns_call_detail_info() {
                assertThrows<CallNotFoundException> {
                    sut.getCallDetailInfo(9999L)
                }
            }
        }
    }
}
