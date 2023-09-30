package hanghae.four.taxiservice.domain.driver

import javax.persistence.*

@Entity
@Table(name = "driver")
class Driver(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
) {
}
