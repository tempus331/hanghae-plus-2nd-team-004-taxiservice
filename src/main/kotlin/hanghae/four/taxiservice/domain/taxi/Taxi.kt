package hanghae.four.taxiservice.domain.taxi

import javax.persistence.*

@Entity
@Table(name = "taxi")
class Taxi(

    @Column(name = "driverId", nullable = false)
    val driverId: Long,

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    val type: Type,

    @Column(name = "number", nullable = false, unique = true)
    val number: Int,

    @Column(name = "status", nullable = false)
    val status: Status,

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    val id: Long? = null
) {

    init {
        if (number <= 0) {
            throw IllegalArgumentException("택시 번호는 0 또는 음수가 될수 없습니다.")
        }
    }

    fun departToCustomer(): Taxi {
        return Taxi(
            driverId = this.driverId,
            type = this.type,
            number = this.number,
            status = Status.ON_WAY_TO_CUSTOMER,
            id = this.id
        )
    }

    enum class Type(
        private val description: String
    ) {
        NORMAL("일반"),
        DELUXE("모범"),
        HIGH("고급")
    }

    enum class Status(
        private val description: String
    ) {
        CLOSED("미운행"),
        WAITING("대기중"),
        RUNNING("운행중"),
        ON_WAY_TO_CUSTOMER("고객에게 가는중"),
        COMPLETE("완료")
    }
}
