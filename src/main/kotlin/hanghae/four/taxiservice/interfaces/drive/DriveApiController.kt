package hanghae.four.taxiservice.interfaces.drive

import com.fasterxml.jackson.databind.ObjectMapper
import hanghae.four.taxiservice.applications.drive.DriveFacade
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class DriveApiController(
    private val driveFacade: DriveFacade,
) {

    @GetMapping("/api/v1/ride/{taxiId}")
    fun getRideData(@PathVariable taxiId: Long): ResponseEntity<*> {
        val data = driveService.getDriveData(taxiId)
        return ResponseEntity.ok(data)
    }
}