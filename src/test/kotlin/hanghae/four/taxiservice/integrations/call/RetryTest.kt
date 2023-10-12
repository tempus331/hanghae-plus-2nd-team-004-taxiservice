package hanghae.four.taxiservice.integrations.call

import com.ninjasquad.springmockk.MockkBean
import hanghae.four.taxiservice.domain.taxi.TaxiFinder
import hanghae.four.taxiservice.domain.taxi.call.CallCommand
import hanghae.four.taxiservice.domain.taxi.call.CallService
import hanghae.four.taxiservice.infrastructures.taxi.call.exception.NotExistsCallableTaxiException
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@DisplayName("Integration: CallService Retry")
@SpringBootTest
class RetryTest {

    @MockkBean
    private lateinit var taxiFinder: TaxiFinder

    @Autowired
    private lateinit var sut: CallService

    @DisplayName("호출 가능한 택시가 존재하지 않을 경우, 최대 5번까지 재시도한다")
    @Test
    fun test() {
        val callCommand = CallCommand(
            origin = "서울시 강서구",
            destination = "서울시 강남구",
            type = "NORMAL",
            userId = 1L
        )

        every { taxiFinder.findUsableTaxis(any()) } returns emptyList()

        assertThrows<NotExistsCallableTaxiException> {
            sut.call(callCommand)
        }

        verify(exactly = 3) { taxiFinder.findUsableTaxis(any()) }
    }
}
