package hanghae.four.taxiservice.infrastructures.driver

import hanghae.four.taxiservice.domain.driver.Driver
import org.springframework.data.jpa.repository.JpaRepository

interface DriverRepository : JpaRepository<Driver, Long>
