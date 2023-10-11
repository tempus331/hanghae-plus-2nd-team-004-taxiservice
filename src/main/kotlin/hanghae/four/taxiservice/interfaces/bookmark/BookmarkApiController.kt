package hanghae.four.taxiservice.interfaces.bookmark

import hanghae.four.taxiservice.domain.bookmark.BookmarkService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BookmarkApiController(
    private val bookmarkService: BookmarkService,
    private val addMapper: ResisterApiMapper,
) {

    @PostMapping("/api/v1/bookmark/register")
    fun addLocation(@RequestBody resisterRequest: ResisterRequest): ResponseEntity<ResisterResponse> {
        val resisterResult = bookmarkService.resister(addMapper.mapToCommand(resisterRequest))
        return ResponseEntity.ok(addMapper.mapToResponse(resisterResult))
    }
}
