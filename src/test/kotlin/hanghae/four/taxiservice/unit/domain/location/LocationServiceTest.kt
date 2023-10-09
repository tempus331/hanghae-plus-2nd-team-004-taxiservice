package hanghae.four.taxiservice.unit.domain.location

import hanghae.four.taxiservice.domain.location.LocationService
import hanghae.four.taxiservice.domain.location.locationCaller.LocationCoordinates
import hanghae.four.taxiservice.unit.domain.location.fakes.FakeLocationApiCaller
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
        locationService = LocationService(fakeLocationApiCaller)
    }

    @Test
    @DisplayName("좌표로 조회 시 현재 위치 조회가 성공한 경우")
    fun `현재 위치 조회가 성공한 경우`() {
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
    @DisplayName("좌표로 조회 시 유효한 범위의 좌표가 아닌 경우")
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
    @DisplayName("키워드로 조회 시 위치 조회가 성공한 경우")
    fun `키워드로 위치 조회가 성공한 경우`() {
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
    @DisplayName("키워드로 조회 시 키워드가 유효하지 않을 경우")
    fun `유효한 키워드가 아닐 때 예외처리 테스트`() {
        // given
        val keyword = ""

        // when
        // then
        assertThrows<IllegalArgumentException> {
            locationService.getCurrentLocationByKeyword(keyword)
        }
    }
}
