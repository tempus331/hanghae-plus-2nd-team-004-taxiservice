package hanghae.four.taxiservice.infrastructure.bookmark

import hanghae.four.taxiservice.domain.bookmark.Bookmark
import org.springframework.data.jpa.repository.JpaRepository

interface BookmarkRepository : JpaRepository<Bookmark, Long>
