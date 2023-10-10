package hanghae.four.taxiservice.interfaces.location

data class AddRequest(
    val clientId : Long,
    val country : String,
    val city : String,
    val placeName : String,
    val latitude : Double,
    val longitude : Double,
)
