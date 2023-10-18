package hanghae.four.taxiservice.domain.bookmark

interface BookmarkReader {
    fun getBookmark(bookmarkId: Long): Bookmark
}
