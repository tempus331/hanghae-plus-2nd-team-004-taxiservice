package hanghae.four.taxiservice.domain.driver

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "driver")
class Driver(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "licenseNumber", nullable = false, unique = true)
    val licenseNumber: String,

    @Column(name = "phoneNumber", nullable = false, unique = true)
    val phoneNumber: String,

    @Column(name = "createdAt", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),
)
