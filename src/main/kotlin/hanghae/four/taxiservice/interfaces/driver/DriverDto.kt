package hanghae.four.taxiservice.interfaces.driver

import hanghae.four.taxiservice.domain.taxi.call.driver.DriverCommand
import javax.validation.constraints.NotNull

class DriverDto {
    data class RegisterRequest(
        @field: NotNull(message = "이름을 입력해주세요.")
        val name: String?,

        @field: NotNull(message = "택시 기사 등록 번호를 입력해주세요.")
        val licenseNumber: String?,

        @field: NotNull(message = "핸드폰 번호를 입력해주세요.")
        val phoneNumber: String?,
    ) {
        fun toDriverCommand(): DriverCommand.RegisterDriver {
            return DriverCommand.RegisterDriver(
                name = requireNotNull(name),
                licenseNumber = requireNotNull(licenseNumber),
                phoneNumber = requireNotNull(phoneNumber)
            )
        }
    }

    data class RegisterResponse(val driverId: Long)
}
