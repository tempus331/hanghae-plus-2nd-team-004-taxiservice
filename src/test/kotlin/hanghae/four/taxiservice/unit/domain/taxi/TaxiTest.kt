package hanghae.four.taxiservice.unit.domain.taxi

import hanghae.four.taxiservice.domain.taxi.Taxi
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class TaxiTest {

    @ParameterizedTest
    @CsvSource("0", "-1", "-1000")
    fun `택시 번호가 0 또는 음수이면 에러`(number: Int) {
        assertThatThrownBy {
            Taxi(
                driverId = 1L,
                type = Taxi.Type.NORMAL,
                number = number,
                status = Taxi.Status.CLOSED
            )
        }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("택시 번호는 0 또는 음수가 될수 없습니다.")
    }
}
