package hanghae.four.taxiservice.interfaces.log

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LogSampleController {
    private val logger: Logger = LoggerFactory.getLogger(LogSampleController::class.java)

    @GetMapping("/api/v1/logs/sample/info")
    fun createInfoSampleLog(): String {
        logger.info("info sample message")
        return "create info sample message"
    }

    @GetMapping("/api/v1/logs/sample/debug")
    fun createDebugSampleLog(): String {
        logger.debug("debug sample message")
        return "create debug sample message"
    }

    @GetMapping("/api/v1/logs/sample/warn")
    fun createWarnSampleLog(): String {
        logger.warn("warn sample message")
        return "create warn sample message"
    }

    @GetMapping("/api/v1/logs/sample/error")
    fun createErrorSampleLog(): String {
        logger.error("error sample message")
        return "create error sample message"
    }
}
