package hanghae.four.taxiservice.domain.drive

import org.springframework.stereotype.Service

@Service
class DriveService(
    val driveReader: DriveReader,
)  {

    fun getDriveData(disPatchId: Long): DriveInfo {

        return driveReader.getDriveData(disPatchId)
    }
}