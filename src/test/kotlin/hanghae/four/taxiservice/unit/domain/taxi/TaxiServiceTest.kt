package hanghae.four.taxiservice.unit.domain.taxi

import hanghae.four.taxiservice.domain.taxi.Taxi
import hanghae.four.taxiservice.domain.taxi.TaxiCommand
import hanghae.four.taxiservice.domain.taxi.TaxiService
import hanghae.four.taxiservice.domain.taxi.TaxiStore
import hanghae.four.taxiservice.unit.infrastructures.taxi.FakeTaxiStore
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

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
}
