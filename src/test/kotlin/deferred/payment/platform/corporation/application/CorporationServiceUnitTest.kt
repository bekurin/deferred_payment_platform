package deferred.payment.platform.corporation.application

import deferred.payment.platform.corporation.domain.Corporation
import deferred.payment.platform.corporation.dto.CorporationCreateRequest
import deferred.payment.platform.corporation.dto.CorporationResponse
import deferred.payment.platform.corporation.infrastructure.CorporationRepository
import deferred.payment.platform.util.Random
import deferred.payment.platform.util.UnitTestBase
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.BDDMockito.any
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito

class CorporationServiceUnitTest : UnitTestBase() {

    @InjectMocks
    private lateinit var corporationService: CorporationService

    @Mock
    private lateinit var corporationRepository: CorporationRepository

    @Test
    fun `기업을 등록할 수 있다`() {
        // given
        val (name, creditLimit) = Pair(Random.createRandomName(), Random.createRandomPositiveBigDecimal())
        val request = CorporationCreateRequest(name, creditLimit)
        val givenCorporation = request.toCorporation()

        val corporationCaptor = ArgumentCaptor.forClass(Corporation::class.java)
        given(corporationRepository.save(any(Corporation::class.java)))
            .willReturn(givenCorporation)

        // when
        val corporationResponse = corporationService.create(request)

        // then
        Mockito.verify(corporationRepository).save(corporationCaptor.capture())
        SoftAssertions.assertSoftly { softly ->
            softly.assertThat(corporationResponse).isEqualTo(CorporationResponse(givenCorporation))
            softly.assertThat(corporationCaptor.value.name).isEqualTo(name)
            softly.assertThat(corporationCaptor.value.creditLimit).isEqualTo(creditLimit)
        }
    }
}
