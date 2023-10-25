package hanghae.four.taxiservice.global.decorator

import org.slf4j.MDC
import org.springframework.core.task.TaskDecorator

class LoggingTaskDecorator : TaskDecorator {
    override fun decorate(runnable: Runnable): Runnable {
        val callerThreadContext = MDC.getCopyOfContextMap()
        return Runnable {
            MDC.setContextMap(callerThreadContext)
            runnable.run()
        }
    }
}
