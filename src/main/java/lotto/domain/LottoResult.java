package lotto.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LottoResult {

    private static final int FLOOR_STANDARD = 100;
    private static final double FLOOR_AFTER_TREATMENT = 100.0;

    private final Map<Rank, Long> ranks;

    public LottoResult(Map<Rank, Long> ranks) {
        this.ranks = new HashMap<>(ranks);
    }

    public double calculateYield(Money money) {
        double totalPrizeMoney = ranks.keySet()
                .stream()
                .mapToDouble(Rank::getPrizeMoney)
                .sum();

        return Math.floor(totalPrizeMoney / money.getPrice() * FLOOR_STANDARD) / FLOOR_AFTER_TREATMENT;
    }

    public Map<Rank, Long> getRanks() {
        return Collections.unmodifiableMap(ranks);
    }
}