package hanghae.four.taxiservice.interfaces.client

import hanghae.four.taxiservice.application.client.ClientFacade
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class ClientApiController(
    private val clientFacade: ClientFacade,
) {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/clients")
    fun register(): ClientDto.RegisterResponse {
        val clientId = clientFacade.register()
        return ClientDto.RegisterResponse(clientId)
    }
}
