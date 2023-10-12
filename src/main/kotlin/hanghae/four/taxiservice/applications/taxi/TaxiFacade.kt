package hanghae.four.taxiservice.applications.taxi

import hanghae.four.taxiservice.domain.taxi.RegisterTaxi
import hanghae.four.taxiservice.domain.taxi.TaxiService
import hanghae.four.taxiservice.utils.annotations.Facade

@Facade
class TaxiFacade(
    private val taxiService: TaxiService,
) {
    fun register(request: RegisterTaxi): Long {
        return taxiService.register(request)
    }
}
