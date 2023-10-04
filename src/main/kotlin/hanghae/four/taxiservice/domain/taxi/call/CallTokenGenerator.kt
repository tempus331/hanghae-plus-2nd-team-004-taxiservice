package hanghae.four.taxiservice.domain.taxi.call

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

val DATE_TIME_PATTERN: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm")

fun generateCallNumber(): String {
    val now = LocalDateTime.now().format(DATE_TIME_PATTERN)
    return UUID.randomUUID().run { now + "-" + this.toString().substring(10) }
}