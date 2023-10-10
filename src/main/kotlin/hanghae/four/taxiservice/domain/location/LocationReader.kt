package hanghae.four.taxiservice.domain.location

import org.springframework.stereotype.Repository

@Repository
interface LocationReader {
    fun getLocation(locationId: Long): Location
}
