package hanghae.four.taxiservice.domain.bookmark

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class BookmarkService(
    private val bookmarkReader: BookmarkReader,
    private val bookmarkStore: BookmarkStore,
) {
    fun register(bookmarkCommand: BookmarkCommand): BookmarkRegisterResult {
        val bookmark = bookmarkStore.store(bookmarkCommand.toEntity())

        return BookmarkRegisterResult(
            locationId = bookmark.id,
            clientId = bookmark.clientId
        )
    }
}
