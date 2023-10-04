package hanghae.four.taxiservice.unit.domain.call

import hanghae.four.taxiservice.domain.taxi.TaxiAllocator
import hanghae.four.taxiservice.domain.taxi.TaxiFinder
import hanghae.four.taxiservice.domain.taxi.call.CallCommand
import hanghae.four.taxiservice.domain.taxi.call.CallService
import hanghae.four.taxiservice.domain.taxi.call.dispatch.DispatchResult
import hanghae.four.taxiservice.domain.taxi.call.dispatch.toCallResult
import hanghae.four.taxiservice.unit.domain.call.fakes.FakeTaxiFinder
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
    private lateinit var sut: CallService

    @BeforeEach
    fun setUp() {
        sut = CallService(taxiFinder, taxiAllocator)
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
                    taxiAllocator.execute(any(), any())
                } throws IllegalArgumentException("호출 가능한 택시가 없습니다.") // 익셉션 발생
                assertThrows<IllegalArgumentException> { sut.call(callCommand) }
            }
        }
    }
}
