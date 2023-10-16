package hanghae.four.taxiservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class TaxiserviceApplication

fun main(args: Array<String>) {
    runApplication<TaxiserviceApplication>(*args)
}
