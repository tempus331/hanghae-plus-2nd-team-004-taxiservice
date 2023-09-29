package hanghae.four.taxiservice.unit.domain.taxi

import hanghae.four.taxiservice.domain.taxi.Taxi
import hanghae.four.taxiservice.domain.taxi.TaxiService
import hanghae.four.taxiservice.domain.taxi.TaxiStore
import hanghae.four.taxiservice.infrastructures.taxi.FakeTaxiStore
import hanghae.four.taxiservice.interfaces.taxi.TaxiDto
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
        val request = TaxiDto.RegisterRequest(type = Taxi.Type.NORMAL, 1L, 1234)
        val taxiId = taxiService.register(request)

        assertThat(taxiId).isEqualTo(1L)
    }
}