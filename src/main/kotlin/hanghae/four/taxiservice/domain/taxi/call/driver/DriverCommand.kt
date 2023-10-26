package hanghae.four.taxiservice.domain.taxi.call.driver

class DriverCommand {
    data class RegisterDriver(
        val name: String?,
        val licenseNumber: String?,
        val phoneNumber: String?,
    )
}
