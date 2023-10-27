package hanghae.four.taxiservice.infrastructure.client

import hanghae.four.taxiservice.domain.client.Client
import hanghae.four.taxiservice.domain.client.ClientStore
import hanghae.four.taxiservice.global.annotations.Store

@Store
class ClientStoreImpl(
    private val clientRepository: ClientRepository,
) : ClientStore {
    override fun store(client: Client): Client {
        return clientRepository.save(client)
    }
}
