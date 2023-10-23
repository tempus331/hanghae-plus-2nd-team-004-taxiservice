package hanghae.four.taxiservice.unit.domain.client

import hanghae.four.taxiservice.domain.client.Client
import hanghae.four.taxiservice.domain.client.ClientService
import hanghae.four.taxiservice.unit.infrastructures.client.FakeClientRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ClientServiceTest {
    private lateinit var fakeClientRepository: FakeClientRepository
    private lateinit var clientService: ClientService

    @BeforeEach
    fun setup() {
        fakeClientRepository = FakeClientRepository()

        clientService = ClientService(fakeClientRepository)

        fakeClientRepository.store(Client())
    }

    @Test
    fun `회원 조회`() {
        val client = clientService.findClient(1L)

        assertThat(client.id).isEqualTo(1L)
    }
}
