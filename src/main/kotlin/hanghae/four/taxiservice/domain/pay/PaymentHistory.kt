package hanghae.four.taxiservice.domain.pay

import hanghae.four.taxiservice.domain.pay.payinfo.Payment
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "payment_history")
class PaymentHistory(
    @Column(name = "client_id", nullable = false)
    val clientId: Long,

    @Column(name = "call_id", nullable = false)
    val callId: Long,

    @Column(name = "type", nullable = false)
    val type: Payment.Type,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
)
