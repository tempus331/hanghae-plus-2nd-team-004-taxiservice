package hanghae.four.taxiservice.unit.infrastructures.call

import hanghae.four.taxiservice.domain.taxi.call.driver.DriverReader
import hanghae.four.taxiservice.domain.taxi.Taxi
import hanghae.four.taxiservice.domain.taxi.TaxiStore
import hanghae.four.taxiservice.domain.taxi.call.CallCommand
import hanghae.four.taxiservice.domain.taxi.call.CallStore
import hanghae.four.taxiservice.infrastructures.taxi.call.RandomDispatcher
import hanghae.four.taxiservice.infrastructures.taxi.call.exception.NotExistsCallableTaxiException
import hanghae.four.taxiservice.unit.infrastructures.driver.FakeDriverRepository
import hanghae.four.taxiservice.unit.infrastructures.taxi.FakeTaxiRepository
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("Unit: RandomDispatcher")
internal class RandomDispatcherTest {

    private lateinit var sut: RandomDispatcher
    private val taxiRepository: TaxiStore = FakeTaxiRepository()
    private val callRepository: CallStore = FakeCallRepository()
    private val driverReader: DriverReader = FakeDriverRepository()

    @BeforeEach
    fun setUp() {
        sut = RandomDispatcher(
            taxiStore = taxiRepository,
            callStore = callRepository,
            driverReader = driverReader
        )
    }

    @DisplayName("support 메서드는")
    @Nested
    inner class Describe_of_support {

        @DisplayName("맞는 타입이 오면,")
        @Nested
        inner class Context_of_valid_type {

            @DisplayName("true를 리턴한다.")
            @Test
            fun it_return_true() {
                val res = sut.supports("RANDOM")
                assertThat(res).isTrue()
            }
        }

        @DisplayName("맞지 않는 타입이 오면,")
        @Nested
        inner class Context_of_invalid_type {

            @DisplayName("false를 리턴한다.")
            @Test
            fun it_return_true() {
                val res = sut.supports("NOT_RANDOM")
                assertThat(res).isFalse()
            }
        }
    }

    @DisplayName("dispatch 메서드는")
    @Nested
    inner class Describe_of_dispatch {

        @DisplayName("택시가 없으면,")
        @Nested
        inner class Context_of_no_taxi {

            @DisplayName("NotExistsCallableTaxiException을 던진다.")
            @Test
            fun it_throw_exception() {
                assertThrows<NotExistsCallableTaxiException> {
                    sut.dispatch(emptyList(), mockk())
                }
            }
        }

        @DisplayName("택시가 있으면,")
        @Nested
        inner class Context_of_exist_taxi {

            @DisplayName("택시를 랜덤하게 선택된 뒤, 출발 상태로 변경된다.")
            @Test
            fun it_return_dispatch_result() {
                val taxiFixture = Taxi(
                    id = 1L,
                    number = 1234,
                    driverId = 1L,
                    type = Taxi.Type.NORMAL,
                    status = Taxi.Status.WAITING
                )

                val callCommandFixture = CallCommand(
                    userId = 1L,
                    type = "NORMAL",
                    origin = "서울시 강남구",
                    destination = "서울시 강북구"
                )

                /**
                 * 고민
                 * 1. store 검증을 이렇게 하는 게 맞을까?
                 * 고민인 이유
                 * 1. 내부 구현이 검증되지 않음
                 * 또 다른 고민
                 * 1. 테스트 코드는 내부 구현을 검증하는 것인가?
                 */
                val res = sut.dispatch(listOf(taxiFixture), callCommandFixture)
                assertThat(res).isNotNull
            }
        }
    }
}
