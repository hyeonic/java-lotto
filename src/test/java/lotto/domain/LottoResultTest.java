package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoResultTest {

    @DisplayName("구입 금액이 주어지면 수익률을 계산하여 반환한다.")
    @Test
    void 수익률_계산_테스트() {
        // given
        Map<Rank, Long> ranks = Map.of(Rank.FIFTH, 1L);
        LottoResult lottoResult = new LottoResult(ranks);
        Money money = new Money(14000);

        // when
        double result = lottoResult.calculateYield(money);

        // then
        assertThat(result).isEqualTo(0.35);
    }
}