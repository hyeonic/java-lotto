package lotto.domain.lotto;

import lotto.domain.lottogenerator.LottoGenerator;
import lotto.domain.lottogenerator.ManualLottoGeneratingStrategy;
import lotto.domain.paymentinfo.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ResultTest {
    private LottoTickets lottoTickets;
    private WinningLotto winningLotto;
    private Result result;

    @BeforeEach
    void setUp() {
        LottoRepository lottoRepository = new LottoRepository();
        lottoRepository.add(LottoGenerator.create(
                new ManualLottoGeneratingStrategy(Arrays.asList(1, 2, 3, 4, 5, 6))));
        lottoTickets = new LottoTickets(lottoRepository);

        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6), false);
        winningLotto = new WinningLotto(lotto, 7);

        result = winningLotto.match(lottoTickets);
    }

    @Test
    void Rank에_따른_당첨_개수_확인() {
        assertThat(result.get(Rank.FIRST)).isEqualTo(1);
    }

    @Test
    void 해당_Rank가_당첨되지_않은_경우() {
        assertThat(result.get(Rank.SECOND)).isEqualTo(0);
        assertThat(result.get(Rank.THIRD)).isEqualTo(0);
        assertThat(result.get(Rank.FOURTH)).isEqualTo(0);
        assertThat(result.get(Rank.MISS)).isEqualTo(0);
    }

    @Test
    void 수익률_계산() {
        assertThat(result.calculateEarningsRate(new Payment(10_000))).isEqualTo(200_000);
    }

    @Test
    void 수익률_계산_잘못된_파라미터() {
        assertThatThrownBy(() -> result.calculateEarningsRate(null)).isInstanceOf(NullPointerException.class);
    }
}