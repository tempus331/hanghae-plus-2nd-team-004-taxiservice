package hanghae.four.taxiservice.interfaces.bookmark

import hanghae.four.taxiservice.domain.bookmark.BookmarkService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BookmarkApiController(
    private val bookmarkService: BookmarkService,
    private val registerApiMapper: RegisterApiMapper,
) {

    @PostMapping("/api/v1/bookmark/register")
    fun resisterBookmark(@RequestBody registerRequest: RegisterRequest): ResponseEntity<RegisterResponse> {
        val resisterResult = bookmarkService.register(registerApiMapper.mapToCommand(registerRequest))
        return ResponseEntity.ok(registerApiMapper.mapToResponse(resisterResult))
    }
}
