package hanghae.four.taxiservice.unit.domain.bookmark

import hanghae.four.taxiservice.domain.bookmark.BookmarkCommand
import hanghae.four.taxiservice.domain.bookmark.BookmarkService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@DisplayName("Unit: BookmarkApiTest")
internal class BookmarkServiceTest {
    @Autowired
    private lateinit var bookmarkService: BookmarkService

    @Test
    @DisplayName("북마크 등록 정상 처리")
    fun `북마크 등록 성공 정상 처리`() {
        val bookmarkCommand = BookmarkCommand(
            client = 1234L,
            country = "대한민국",
            city = "서울특별시",
            placeName = "서울시청",
            latitude = 37.501952,
            longitude = 127.044529
        )

        val registerBookmarkResult = bookmarkService.register(bookmarkCommand)

        assertEquals(1234L, registerBookmarkResult.clientId)
    }
}
