package hanghae.four.taxiservice.infrastructure.client

import hanghae.four.taxiservice.domain.client.Client
import hanghae.four.taxiservice.domain.client.ClientReader
import hanghae.four.taxiservice.infrastructure.util.findByIdOrThrow
import org.springframework.stereotype.Repository

@Repository
class ClientReaderImpl(
    private val clientRepository: ClientRepository,
) : ClientReader {

    override fun getClient(clientId: Long): Client {
        return clientRepository.findByIdOrThrow(clientId)
    }
}
