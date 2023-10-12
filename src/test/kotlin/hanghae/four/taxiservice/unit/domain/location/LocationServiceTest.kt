package hanghae.four.taxiservice.unit.domain.location

import hanghae.four.taxiservice.domain.location.LocationService
import hanghae.four.taxiservice.domain.location.locationCaller.LocationCoordinates
import hanghae.four.taxiservice.unit.domain.location.fakes.FakeLocationApiCaller
import hanghae.four.taxiservice.unit.domain.location.fakes.FakeTaxiFareCalculationApiCaller
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("Unit: LocationApiTest")
internal class LocationServiceTest {
    private lateinit var locationService: LocationService

    @BeforeEach
    fun setUp() {
        val fakeLocationApiCaller = FakeLocationApiCaller()
        val fakeTaxiFareCalculationApiCaller = FakeTaxiFareCalculationApiCaller()
        locationService = LocationService(fakeLocationApiCaller, fakeTaxiFareCalculationApiCaller)
    }

    @Test
    @DisplayName("좌표로 조회 시 현재 위치 조회가 성공할 경우 정상 처리")
    fun `현재 위치 조회가 성공한 정상_처리`() {
        // given
        val coordinates = LocationCoordinates(latitude = "37.123", longitude = "-122.456")

        // when
        val currentLocation = locationService.getCurrentLocationByCoordinates(coordinates)

        // then
        assertEquals("123 Main Street", currentLocation.addressName)
        assertEquals("Main Street 12-3", currentLocation.roadAddressName)
        assertEquals("Sample Location", currentLocation.name)
        assertEquals(coordinates.latitude, currentLocation.latitude)
        assertEquals(coordinates.longitude, currentLocation.longitude)
    }

    @Test
    @DisplayName("좌표로 조회 시 유효한 범위의 좌표가 아닌 경우 예외 처리")
    fun `유효한 좌표가 아닐 때 예외처리 테스트`() {
        // given
        val coordinates = LocationCoordinates(latitude = "1000.0", longitude = "2000.0")

        // when
        // then
        assertThrows<IllegalArgumentException> {
            locationService.getCurrentLocationByCoordinates(coordinates)
        }
    }

    @Test
    @DisplayName("키워드로 조회 시 위치 조회가 성공한 경우 정상 처리")
    fun `키워드로 위치 조회가 성공한 경우_정상_처리`() {
        // given
        val keyword = "Sample Location"

        // when
        val currentLocation = locationService.getCurrentLocationByKeyword(keyword)

        // then
        assertEquals("123 Main Street", currentLocation.addressName)
        assertEquals("Main Street 12-3", currentLocation.roadAddressName)
        assertEquals("Sample Location", currentLocation.name)
        assertEquals("37.123", currentLocation.latitude)
        assertEquals("-122.456", currentLocation.longitude)
    }

    @Test
    @DisplayName("키워드로 조회 시 키워드가 유효하지 않을 경우 예외 처리")
    fun `유효한 키워드가 아닐 때 예외_처리 테스트`() {
        // given
        val keyword = ""

        // when
        // then
        assertThrows<IllegalArgumentException> {
            locationService.getCurrentLocationByKeyword(keyword)
        }
    }

    @Test
    @DisplayName("택시 요금 계산 API 호출 시 유효한 좌표 값일 땐 요금 리턴")
    fun `택시 요금 계산 API 호출에서 유효한 좌표는 요금 리턴`() {
        // given
        val originCoordinates = LocationCoordinates(latitude = "12.345", longitude = "-45.678")
        val destinationCoordinates = LocationCoordinates(latitude = "37.123", longitude = "-122.456")

        // when
        val fare = locationService.getCalculatedTaxFare(originCoordinates, destinationCoordinates)

        // then
        assertEquals(15000L, fare)
    }

    @Test
    @DisplayName("택시 요금 계산 API 호출 시 유효한 좌표 값이 아닐 때 예외 처리")
    fun `택시 요금 계산 API 호출에서 유효하지 않은 좌표는 예외 처리`() {
        // given
        val originCoordinates = LocationCoordinates(latitude = "1000.0", longitude = "2000.0")
        val destinationCoordinates = LocationCoordinates(latitude = "37.123", longitude = "-122.456")

        // when
        // then
        assertThrows<IllegalArgumentException> {
            locationService.getCalculatedTaxFare(originCoordinates, destinationCoordinates)
        }
    }
}
