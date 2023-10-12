package hanghae.four.taxiservice.domain.bookmark

import hanghae.four.taxiservice.domain.bookmark.location.Location
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "bookmark")
class Bookmark(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "client_id", nullable = false)
    val clientId: Long,

    @ManyToOne
    @JoinColumn(name = "location_id")
    val location: Location,
)
