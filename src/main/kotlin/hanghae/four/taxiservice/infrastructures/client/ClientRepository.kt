package hanghae.four.taxiservice.infrastructures.client

import hanghae.four.taxiservice.domain.client.Client
import org.springframework.data.jpa.repository.JpaRepository

interface ClientRepository : JpaRepository<Client, Long>
