package hanghae.four.taxiservice.infrastructures.bookmark

import hanghae.four.taxiservice.domain.bookmark.Bookmark
import hanghae.four.taxiservice.domain.bookmark.BookmarkStore
import org.springframework.stereotype.Repository

@Repository
class BookmarkStoreImpl(
    private val bookmarkRepository: BookmarkRepository,
) : BookmarkStore {

    override fun store(bookmark: Bookmark): Bookmark {
        return bookmarkRepository.save(bookmark)
    }
}
