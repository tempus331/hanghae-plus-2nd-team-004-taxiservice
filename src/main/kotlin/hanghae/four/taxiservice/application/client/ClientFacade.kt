package hanghae.four.taxiservice.application.client

import hanghae.four.taxiservice.domain.client.ClientService
import hanghae.four.taxiservice.global.annotations.Facade

@Facade
class ClientFacade(
    private val clientService: ClientService,
) {
    fun register(): Long {
        return clientService.register()
    }
}
