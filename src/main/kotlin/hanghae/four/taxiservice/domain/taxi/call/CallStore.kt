package hanghae.four.taxiservice.domain.taxi.call

interface CallStore {
    fun store(call: Call): Call
}
