package lotto.controller.dto;

import static java.util.stream.Collectors.*;

import java.util.Collections;
import java.util.List;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;

public class LottoTicketsDto {

    private final List<List<Integer>> lottoTickets;

    private LottoTicketsDto(LottoTickets lottoTickets) {
        this.lottoTickets = lottoTickets.getLottoTickets()
                .stream()
                .map(this::toLottoNumbers)
                .collect(toList());
    }

    private List<Integer> toLottoNumbers(LottoTicket lottoTicket) {
        return lottoTicket.getLottoNumbers()
                .stream()
                .map(LottoNumber::getLottoNumber)
                .collect(toList());
    }

    public static LottoTicketsDto from(LottoTickets lottoTickets) {
        return new LottoTicketsDto(lottoTickets);
    }

    public LottoTickets toLottoTickets() {
        List<List<LottoNumber>> lottoNumbers = lottoTickets.stream()
                .map(this::parseLottoNumbers)
                .collect(toList());

        return new LottoTickets(lottoNumbers);
    }

    private List<LottoNumber> parseLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::from)
                .collect(toList());
    }

    public List<List<Integer>> getLottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }
}
