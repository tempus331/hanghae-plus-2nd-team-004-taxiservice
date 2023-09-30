package hanghae.four.taxiservice.domain.drive

interface DriveReader {

    fun getDriveData(disPatchId: Long): DriveInfo
}