package hanghae.four.taxiservice.domain.taxi.call.driver

class DriverCommand {
    data class RegisterDriver(
        val name: String?,
        val licenseNumber: String?,
        val phoneNumber: String?,
    ) {
        fun toEntity(): Driver {
            return Driver(
                name = requireNotNull(name),
                licenseNumber = requireNotNull(licenseNumber),
                phoneNumber = requireNotNull(phoneNumber)
            )
        }
    }
}
