package hanghae.four.taxiservice.domain.client

interface ClientStore {
    fun store(client: Client): Client
}
