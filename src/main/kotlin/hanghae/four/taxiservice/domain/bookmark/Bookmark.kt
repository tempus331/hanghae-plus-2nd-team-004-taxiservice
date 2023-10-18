package hanghae.four.taxiservice.domain.bookmark

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "bookmark")
class Bookmark(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "client_id", nullable = false)
    val clientId: Long,

    @Column(name = "placeName", nullable = true)
    val placeName: String,

    @Column(name = "latitude", nullable = true)
    val latitude: Double,

    @Column(name = "longitude", nullable = true)
    val longitude: Double,
)
