package hanghae.four.taxiservice.domain.client

import org.springframework.stereotype.Service

@Service
class ClientService(
    val clientReader: ClientReader,
    val clientStore: ClientStore,
) {
    fun findClient(clientId: Long): ClientInfo {
        val client = clientReader.findClient(clientId)
        return ClientInfo(requireNotNull(client.id))
    }

    fun register(): Long {
        val client = clientStore.store(Client())
        return requireNotNull(client.id)
    }
}
