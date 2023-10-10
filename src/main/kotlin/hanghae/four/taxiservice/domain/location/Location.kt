package hanghae.four.taxiservice.domain.location

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "location")
class Location (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "country", nullable = true)
    val country: String,

    @Column(name = "city", nullable = true)
    val city: String,

    @Column(name = "placeName", nullable = true)
    val placeName: String,

    @Column(name = "latitude", nullable = true)
    val latitude: Double,

    @Column(name = "longitude", nullable = true)
    val longitude: Double,
)
