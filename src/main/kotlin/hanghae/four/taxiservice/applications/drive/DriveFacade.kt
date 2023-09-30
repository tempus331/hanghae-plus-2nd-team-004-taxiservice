package hanghae.four.taxiservice.applications.drive

import hanghae.four.taxiservice.domain.drive.DriveInfo
import hanghae.four.taxiservice.domain.drive.DriveService
import org.springframework.stereotype.Component

@Component
class DriveFacade(
    val driveService: DriveService,
) {
    /**
     *  배차 Id를 통해 조회
     */
    fun getDriveInfoByDispatchId (disPatchId: Long): DriveInfo {
        return driveService.getDriveData(disPatchId)
    }
}