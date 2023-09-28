package hanghae.four.taxiservice.domain.taxi

class Taxi(

) {

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
        COMPLETE("완료")
    }
}
