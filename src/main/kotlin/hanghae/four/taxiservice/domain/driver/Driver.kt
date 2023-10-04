package hanghae.four.taxiservice.domain.driver

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

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
