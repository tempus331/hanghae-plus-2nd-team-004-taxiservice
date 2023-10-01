package hanghae.four.taxiservice.unit.domain.taxi

import hanghae.four.taxiservice.domain.driver.DriverReader
import hanghae.four.taxiservice.domain.taxi.*
import hanghae.four.taxiservice.unit.infrastructures.driver.FakeDriverRepository
import hanghae.four.taxiservice.unit.infrastructures.taxi.FakeTaxiRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.persistence.EntityExistsException

internal class TaxiServiceTest {
    private lateinit var taxiService: TaxiService

    private lateinit var taxiStore: TaxiStore

    private lateinit var taxiReader: TaxiReader

    private lateinit var driverReader: DriverReader

    @BeforeEach
    fun setup() {
        taxiStore = FakeTaxiRepository()
        taxiReader = taxiStore as FakeTaxiRepository
        driverReader = FakeDriverRepository()
        taxiService = TaxiService(driverReader, taxiStore, taxiReader)
    }

    @Test
    fun `택시 등록`() {
        val request = RegisterTaxi(1L, type = Taxi.Type.NORMAL, 1234)
        val taxiId = taxiService.register(request)

        assertThat(taxiId).isEqualTo(1L)
    }

    @Test
    fun `택시기사가 존재하지 않다면 에러`() {
        val request = RegisterTaxi(2L, type = Taxi.Type.NORMAL, 1234)

        assertThatThrownBy{taxiService.register(request)}
            .isInstanceOf(java.lang.IllegalArgumentException::class.java)
    }

    @Test
    fun `택시번호 중복시 에러`() {
        taxiStore.store(Taxi(1L, Taxi.Type.NORMAL, 1234, Taxi.Status.CLOSED))

        val request = RegisterTaxi(1L, type = Taxi.Type.NORMAL, 1234)

        assertThatThrownBy{taxiService.register(request)}
            .isInstanceOf(EntityExistsException::class.java)
            .hasMessage("중복된 택시 번호가 있습니다.")
    }
}
