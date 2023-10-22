package hanghae.four.taxiservice.domain.client

import org.springframework.stereotype.Service

@Service
class ClientService(
    val clientReader: ClientReader,
) {
    fun findClient(clientId: Long): ClientInfo {
        val client = clientReader.findClient(clientId)
        return ClientInfo(requireNotNull(client.id))
    }
}
