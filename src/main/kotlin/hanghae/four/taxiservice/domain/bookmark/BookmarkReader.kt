package hanghae.four.taxiservice.domain.bookmark

import org.springframework.stereotype.Repository

@Repository
interface BookmarkReader {
    fun getBookmark(bookmarkId: Long): Bookmark
}
