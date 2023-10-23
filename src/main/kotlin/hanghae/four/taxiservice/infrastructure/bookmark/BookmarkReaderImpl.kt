package hanghae.four.taxiservice.infrastructure.bookmark

import hanghae.four.taxiservice.domain.bookmark.Bookmark
import hanghae.four.taxiservice.domain.bookmark.BookmarkReader
import hanghae.four.taxiservice.infrastructure.util.findByIdOrThrow
import org.springframework.stereotype.Repository

@Repository
class BookmarkReaderImpl(
    private val bookmarkRepository: BookmarkRepository,
) : BookmarkReader {
    override fun getBookmark(bookmarkId: Long): Bookmark {
        return bookmarkRepository.findByIdOrThrow(bookmarkId)
    }
}
