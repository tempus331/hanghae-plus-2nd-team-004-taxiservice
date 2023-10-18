package hanghae.four.taxiservice.domain.bookmark

interface BookmarkStore {
    fun store(bookmark: Bookmark): Bookmark
}
