package hanghae.four.taxiservice.global.config

import hanghae.four.taxiservice.global.decorator.LoggingTaskDecorator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.TaskExecutor
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

@Configuration
class AsyncConfig {
    @Bean
    fun taskExecutor(): TaskExecutor {
        val taskExecutor = ThreadPoolTaskExecutor()
        taskExecutor.corePoolSize = 20
        taskExecutor.queueCapacity = 200
        taskExecutor.maxPoolSize = 50
        taskExecutor.setTaskDecorator(LoggingTaskDecorator())

        return taskExecutor
    }
}
