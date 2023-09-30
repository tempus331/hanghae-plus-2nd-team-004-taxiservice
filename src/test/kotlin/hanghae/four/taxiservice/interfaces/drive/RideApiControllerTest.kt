package hanghae.four.taxiservice.interfaces.drive

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.filter.CharacterEncodingFilter

@DisplayName("[Integration] RideApiController")
@SpringBootTest
class RideApiControllerTest {

    lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setUp(webApplicationContext: WebApplicationContext) {
        mockMvc = MockMvcBuilders
            .webAppContextSetup(webApplicationContext)
            .addFilter<DefaultMockMvcBuilder>(CharacterEncodingFilter("UTF-8"))
            .alwaysDo<DefaultMockMvcBuilder>(MockMvcResultHandlers.print())
            .build()
    }

    @DisplayName("라이드 데이터 조회 API 테스트")
    @Nested
    inner class Describe_of_ride_data_call_api() {

        @DisplayName("라이드 데이터 조회 API 테스트")
        @Nested
        inner class Context_of_ride_data_call_api() {

            @Test
            fun in_return_ride_data() {
                mockMvc.get("/api/v1/ride") {

                }.andExpect {
                    status { isOk() }
                }
            }
        }
    }
}