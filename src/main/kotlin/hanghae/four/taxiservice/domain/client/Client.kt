package hanghae.four.taxiservice.domain.client

import javax.persistence.*

@Entity
@Table(name = "client")
class Client(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
) {
}
