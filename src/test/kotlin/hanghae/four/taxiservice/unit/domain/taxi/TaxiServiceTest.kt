package hanghae.four.taxiservice.unit.domain.taxi

import hanghae.four.taxiservice.domain.taxi.Taxi
import hanghae.four.taxiservice.domain.taxi.TaxiCommand
import hanghae.four.taxiservice.domain.taxi.TaxiService
import hanghae.four.taxiservice.domain.taxi.TaxiStore
import hanghae.four.taxiservice.unit.infrastructures.taxi.FakeTaxiStore
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.dao.DataIntegrityViolationException

internal class TaxiServiceTest {
    private lateinit var taxiService: TaxiService

    private lateinit var taxiStore: TaxiStore

    @BeforeEach
    fun setup() {
        taxiStore = FakeTaxiStore()
        taxiService = TaxiService(taxiStore)
    }

    @Test
    fun `택시 등록`() {
        val request = TaxiCommand.RegisterTaxi(1L, type = Taxi.Type.NORMAL, 1234)
        val taxiId = taxiService.register(request)

        assertThat(taxiId).isEqualTo(1L)
    }

    @Test
    fun `택시번호 중복시 에러`() {
        taxiStore.store(Taxi(1L, Taxi.Type.NORMAL, 1234, Taxi.Status.CLOSED))

        val request = TaxiCommand.RegisterTaxi(1L, type = Taxi.Type.NORMAL, 1234)

        assertThatThrownBy{taxiService.register(request)}
            .isInstanceOf(DataIntegrityViolationException::class.java)
            .hasMessage("중복된 택시 번호가 있습니다.")
    }
}
