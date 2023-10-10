package hanghae.four.taxiservice.domain.location

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.random.Random


@Service
@Transactional
class LocationService {
    fun add(addCommand: AddCommand) : AddResult {
        // 저장 부분 추가 예정
        // 임시로 저장된 location id 생성
        val locationId = Random.nextLong(1_000_000_000, 10_000_000_000)
        return AddResult(
            locationId = locationId,
            clientId = addCommand.client,
        )
    }
}
