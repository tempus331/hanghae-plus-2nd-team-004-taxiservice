package hanghae.four.taxiservice.unit.domain.call.fakes

import hanghae.four.taxiservice.domain.taxi.Taxi
import hanghae.four.taxiservice.domain.taxi.TaxiFinder

class FakeTaxiFinder: TaxiFinder {
    override fun findUsableTaxis(type: String): List<Taxi> {
        return listOf(Taxi(driverId = 1L, type = Taxi.Type.NORMAL, status = Taxi.Status.WAITING, number = 1))
    }
}