package hanghae.four.taxiservice.domain.taxi

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {

    init {
        if (number <= 0) {
            throw IllegalArgumentException("택시 번호는 0 또는 음수가 될수 없습니다.")
        }
    }

    enum class Type(
        private val description: String,
    ) {
        NORMAL("일반"),
        DELUXE("모범"),
        HIGH("고급"),
    }

    enum class Status(
        private val description: String,
    ) {
        CLOSED("미운행"),
        WAITING("대기중"),
        RUNNING("운행중"),
        COMPLETE("완료"),
    }
}
