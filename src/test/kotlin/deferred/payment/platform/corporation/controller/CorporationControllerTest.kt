package deferred.payment.platform.corporation.controller

import deferred.payment.platform.corporation.application.CorporationService
import deferred.payment.platform.corporation.dto.CorporationCreateRequest
import deferred.payment.platform.corporation.dto.CorporationResponse
import deferred.payment.platform.util.IntegrationTestBase
import deferred.payment.platform.util.Random
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class CorporationControllerTest : IntegrationTestBase() {

    @MockBean
    private lateinit var corporationService: CorporationService

    @Nested
    inner class `기업 등록 API를 호출할 때` {

        @ParameterizedTest
        @ValueSource(strings = ["      ", ""])
        fun `이름이 공백이거나 빈 문자열인 경우 예외가 발생한다`(invalidName: String) {
            // given
            val creditLimit = Random.createRandomPositiveBigDecimal()
            val request = mapOf(
                "name" to invalidName,
                "creditLimit" to creditLimit
            )

            // when
            val performed = mockMvc.perform(
                post("/v1/corporations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsBytes(request))
            )

            // then
            performed.andExpect(status().isBadRequest)
        }

        @ParameterizedTest
        @ValueSource(longs = [0, -1, -2])
        fun `신용 한도가 0 이하인 경우 예외가 발생한다`(invalidCreditLimit: Long) {
            // given
            val name = Random.createRandomName()
            val request = mapOf(
                "name" to name,
                "creditLimit" to invalidCreditLimit
            )

            // when
            val performed = mockMvc.perform(
                post("/v1/corporations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsBytes(request))
            )

            // then
            performed.andExpect(status().isBadRequest)
        }

        @Test
        fun `이름이 공백이 아니고, 신용 한도가 1 이상이라면 정상적으로 호출한다`() {
            // given
            val (name, creditLimit) = Pair(Random.createRandomName(), Random.createRandomPositiveBigDecimal())
            val request = CorporationCreateRequest(name, creditLimit)
            given(corporationService.create(request))
                .willReturn(CorporationResponse(request.toCorporation()))

            // when
            val performed = mockMvc.perform(
                post("/v1/corporations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsBytes(request))
            )

            // then
            val response =
                objectMapper.readValue(performed.andReturn().response.contentAsString, CorporationResponse::class.java)
            SoftAssertions.assertSoftly { softly ->
                softly.assertThat(response.name).isEqualTo(name)
                softly.assertThat(response.creditLimit).isEqualTo(creditLimit)
            }
        }
    }

}
