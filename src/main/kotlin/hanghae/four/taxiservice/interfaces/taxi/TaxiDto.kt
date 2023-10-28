package hanghae.four.taxiservice.interfaces.taxi

import hanghae.four.taxiservice.domain.taxi.RegisterTaxi
import hanghae.four.taxiservice.domain.taxi.Taxi
import javax.validation.constraints.NotNull

data class RegisterRequest(
    @field: NotNull(message = "택시 종류는 필수값 입니다.")
    val type: Taxi.Type?,

    @field: NotNull(message = "택시기사 정보 id는 필수값 입니다.")
    val driver: Long?,

    @field: NotNull(message = "택시 번호는 필수값 입니다.")
    val number: Int?,
) {
    fun toTaxiCommand(): RegisterTaxi {
        return RegisterTaxi(
            driverId = requireNotNull(driver),
            type = requireNotNull(type),
            number = requireNotNull(number)
        )
    }
}

data class RegisterResponse(val taxiId: Long)

data class TaxiResponse(
    val taxiId: Long,
    val type: Taxi.Type,
    val number: Int,
    val status: Taxi.Status,
)
