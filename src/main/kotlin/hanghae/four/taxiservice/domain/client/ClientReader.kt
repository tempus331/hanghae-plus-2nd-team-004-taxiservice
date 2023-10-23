package hanghae.four.taxiservice.domain.client

interface ClientReader {
    fun getClient(clientId: Long): Client
    fun findClient(clientId: Long): Client
}
