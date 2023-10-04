package hanghae.four.taxiservice.domain.taxi.call

interface CallReader {
    fun getById(taxiId: Long): Call
}
