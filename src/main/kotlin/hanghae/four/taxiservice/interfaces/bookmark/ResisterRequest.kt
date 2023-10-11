package hanghae.four.taxiservice.interfaces.bookmark

data class ResisterRequest(
    val clientId : Long,
    val country : String,
    val city : String,
    val placeName : String,
    val latitude : Double,
    val longitude : Double,
)
