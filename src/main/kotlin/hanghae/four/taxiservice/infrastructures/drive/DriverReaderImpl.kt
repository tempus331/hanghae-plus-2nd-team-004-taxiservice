package hanghae.four.taxiservice.infrastructures.drive

import hanghae.four.taxiservice.domain.drive.DriveInfo
import hanghae.four.taxiservice.domain.drive.DriveReader
import org.springframework.stereotype.Component

@Component
class DriverReaderImpl(private val driverRepository: DriveJpaRepository) : DriveReader  {

    override fun getDriveData(disPatchId: Long): DriveInfo {
        ¬
    }
}