package hanghae.four.taxiservice.interfaces.bookmark

import hanghae.four.taxiservice.domain.bookmark.BookmarkService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BookmarkApiController(
    private val bookmarkService: BookmarkService,
    private val resisterApiMapper: ResisterApiMapper,
) {

    @PostMapping("/api/v1/bookmark/register")
    fun resisterBookmark(@RequestBody resisterRequest: ResisterRequest): ResponseEntity<ResisterResponse> {
        val resisterResult = bookmarkService.resister(resisterApiMapper.mapToCommand(resisterRequest))
        return ResponseEntity.ok(resisterApiMapper.mapToResponse(resisterResult))
    }
}
