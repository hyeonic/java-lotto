package lotto.domain.result;

import lotto.domain.Money;

import java.util.*;

import static lotto.domain.result.Rank.RANK_END_INDEX;
import static lotto.domain.result.Rank.RANK_START_INDEX;

public class LottoStatistics {
	private static final int STATISTICS_INITIAL_VALUE = 0;

	private final Map<Rank, Integer> statistics = new LinkedHashMap<>();
	private final Money moneyInvested;

	public LottoStatistics(List<Rank> ranks, Money money) {
		initializeEmptyStatistics();
		aggregateStatisticsBasedOn(ranks);
		this.moneyInvested = money;
	}

	private void initializeEmptyStatistics() {
		for (Rank rank : Rank.values()) {
			statistics.put(rank, STATISTICS_INITIAL_VALUE);
		}
	}

	private void aggregateStatisticsBasedOn(List<Rank> ranks) {
		for (Rank rank : ranks) {
			statistics.put(rank, statistics.get(rank) + 1);
		}
	}

	public List<Integer> getWinCountsByRank() {
		List<Integer> allWinCountsByRank = new ArrayList<>(statistics.values());
		List<Integer> winCountsByRankForStatistics = allWinCountsByRank.subList(RANK_START_INDEX, RANK_END_INDEX);
		Collections.reverse(winCountsByRankForStatistics);
		return winCountsByRankForStatistics;
	}

	public float getProfitRate() {
		Money totalProfit = getTotalProfit();
		return moneyInvested.divide(totalProfit);
	}

	private Money getTotalProfit() {
		long totalProfit = 0;
		for (Rank rank : statistics.keySet()) {
			Integer winCount = statistics.get(rank);
			int prize = rank.getPrize();
			totalProfit += (long) winCount * prize;
		}
		return new Money(totalProfit);
	}
}