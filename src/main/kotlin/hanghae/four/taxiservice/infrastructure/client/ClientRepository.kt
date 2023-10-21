package hanghae.four.taxiservice.infrastructure.client

import hanghae.four.taxiservice.domain.client.Client
import org.springframework.data.jpa.repository.JpaRepository

interface ClientRepository : JpaRepository<Client, Long>
