package hanghae.four.taxiservice.unit.domain.bookmark

import hanghae.four.taxiservice.domain.bookmark.Bookmark
import hanghae.four.taxiservice.domain.bookmark.BookmarkCommand
import hanghae.four.taxiservice.domain.bookmark.BookmarkReader
import hanghae.four.taxiservice.domain.bookmark.BookmarkService
import hanghae.four.taxiservice.domain.bookmark.BookmarkStore
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Unit: BookmarkApiTest")
internal class BookmarkServiceTest {

    private val bookmarkReader: BookmarkReader = mockk<BookmarkReader>()
    private val bookmarkStore: BookmarkStore = mockk<BookmarkStore>()
    private lateinit var bookmarkService: BookmarkService

    @BeforeEach
    fun setUp() {
        bookmarkService = BookmarkService(bookmarkReader, bookmarkStore)
    }

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

        every { bookmarkStore.store(any()) } returns Bookmark(1L, 1234L, "서울시청", 37.501952, 127.044529)

        val registerBookmarkResult = bookmarkService.register(bookmarkCommand)

        assertEquals(1234L, registerBookmarkResult.clientId)
    }
}
