package hanghae.four.taxiservice.domain.taxi.call

import hanghae.four.taxiservice.domain.taxi.TaxiReader
import org.springframework.stereotype.Component

@Component
class TaxiCallValidator(val taxiReader: TaxiReader){


}