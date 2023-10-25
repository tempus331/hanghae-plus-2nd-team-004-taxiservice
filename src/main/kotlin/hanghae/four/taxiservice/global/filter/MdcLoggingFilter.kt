package hanghae.four.taxiservice.global.filter

import org.slf4j.MDC
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import java.util.UUID
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class MdcLoggingFilter : Filter {
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val uuid = UUID.randomUUID()
        MDC.put("request_id", uuid.toString())
        chain?.doFilter(request, response)
        MDC.clear()
    }
}
