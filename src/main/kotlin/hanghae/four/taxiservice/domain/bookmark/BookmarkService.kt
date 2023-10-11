package hanghae.four.taxiservice.domain.bookmark

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.random.Random

@Service
@Transactional
class BookmarkService {
    fun register(bookmarkCommand: BookmarkCommand): BookmarkRegisterResult {
        // 저장 부분 추가 예정
        // 임시로 저장된 location id 생성
        val locationId = Random.nextLong(1_000_000_000, 10_000_000_000)
        return BookmarkRegisterResult(
            locationId = locationId,
            clientId = bookmarkCommand.client
        )
    }
}
