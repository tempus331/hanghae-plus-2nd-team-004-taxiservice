package hanghae.four.taxiservice.acceptance

import io.restassured.RestAssured
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AcceptanceTest {

    @LocalServerPort
    var port: Int = 0

    @Autowired
    private lateinit var databaseCleanup: DatabaseCleanup

    @BeforeEach
    fun setUp() {
        RestAssured.port = port
        databaseCleanup.execute()
    }
}
