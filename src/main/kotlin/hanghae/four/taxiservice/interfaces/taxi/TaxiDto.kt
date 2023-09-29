package hanghae.four.taxiservice.interfaces.taxi

import hanghae.four.taxiservice.domain.taxi.Taxi
import hanghae.four.taxiservice.domain.taxi.TaxiCommand
import javax.validation.constraints.NotNull

class TaxiDto {

    data class RegisterRequest(
        @field: NotNull(message = "택시 종류는 필수값 입니다.")
        val type: Taxi.Type?,

        @field: NotNull(message = "택시기사 정보 id는 필수값 입니다.")
        val driver: Long?,

        @field: NotNull(message = "택시 번호는 필수값 입니다.")
        val number: Int?
    ) {
        fun toTaxiCommand(): TaxiCommand.RegisterTaxi {
            return TaxiCommand.RegisterTaxi(
                driverId = requireNotNull(driver),
                type = requireNotNull(type),
                number = requireNotNull(number)
            )
        }
    }

    data class RegisterResponse(
        val taxiId: Long
    ) {}
}