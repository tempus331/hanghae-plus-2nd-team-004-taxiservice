package hanghae.four.taxiservice.unit.domain.call.allocator

import hanghae.four.taxiservice.domain.taxi.Taxi
import hanghae.four.taxiservice.domain.taxi.TaxiAllocator
import hanghae.four.taxiservice.domain.taxi.call.CallCommand
import hanghae.four.taxiservice.domain.taxi.call.dispatch.TaxiDispatcher
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@DisplayName("Unit TaxiAllocator 테스트")
@ExtendWith(MockitoExtension::class)
class TaxiAllocatorTests {

    private lateinit var sut: TaxiAllocator
    private var dispatcher: TaxiDispatcher = mockk()

    @BeforeEach
    fun setUp() {
        sut = TaxiAllocator(listOf(dispatcher))
    }

    @DisplayName("findDispatchableTaxi 메서드는")
    @Nested
    inner class Describe_of_findDispatchableTaxi {

        @DisplayName("잘못된 Call Type을 받으면")
        @Nested
        inner class Context_of_invalid_callType {

            @DisplayName("예외가 발생한다.")
            @Test
            fun it_throw_exception() {
                val mockTaxi = mockk<Taxi>()
                val callCommand = mockk<CallCommand>()

                every { dispatcher.supports(any()) } returns false
                assertThrows<IllegalArgumentException> { sut.execute(listOf(mockTaxi), callCommand) }
            }
        }
    }
}
