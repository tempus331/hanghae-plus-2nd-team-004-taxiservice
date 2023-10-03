package hanghae.four.taxiservice.integrations

import hanghae.four.taxiservice.domain.driver.Driver
import hanghae.four.taxiservice.domain.taxi.Taxi
import hanghae.four.taxiservice.infrastructures.driver.DriverRepository
import hanghae.four.taxiservice.infrastructures.taxi.TaxiRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

private lateinit var NORMAL_WAITING_STATUS_TAXI: Taxi
private lateinit var DELUXE_WAITING_STATUS_TAXI: Taxi
private lateinit var TEST_DRIVER: Driver
private lateinit var TEST_DRIVER_02: Driver

@Component
class DatabasePreparer {

    @Autowired
    private lateinit var taxiRepository: TaxiRepository

    @Autowired
    private lateinit var driveRepository: DriverRepository

    fun execute() {
        prepareDrivers()
        prepareTaxis()
    }

    private fun prepareTaxis() {
        NORMAL_WAITING_STATUS_TAXI = taxiRepository.save(Taxi(driverId = TEST_DRIVER.id!!, type = Taxi.Type.NORMAL, number = 12, status = Taxi.Status.WAITING))
        DELUXE_WAITING_STATUS_TAXI = taxiRepository.save(Taxi(driverId = TEST_DRIVER_02.id!!, type = Taxi.Type.NORMAL, number = 13, status = Taxi.Status.WAITING))
    }

    private fun prepareDrivers() {
        TEST_DRIVER = driveRepository.save(Driver(name = "홍길동", phoneNumber = "010-1234-5678", licenseNumber = "1234"))
        TEST_DRIVER_02 = driveRepository.save(Driver(name = "가나다", phoneNumber = "010-5431-5678", licenseNumber = "1234-002"))
    }
}
