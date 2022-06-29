package com.example.kakao.service;


import com.example.kakao.dto.*;
import com.example.kakao.exception.ExceptionCode;
import com.example.kakao.exception.NotFoundBankException;
import com.example.kakao.model.Amount;
import com.example.kakao.model.Bank;
import com.example.kakao.model.InstituteCode;
import com.example.kakao.repository.AmountRepository;
import com.example.kakao.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.summingInt;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;
    private final AmountRepository amountRepository;
    @Override
    @Transactional(readOnly = true)
    public List<ResponseBank> findAllBanks(){
        return this.bankRepository.findAll().stream()
                .map(ResponseBank::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseSupply statisticsSupply() {
        Map<Integer, List<Amount>> collect = this.amountRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(amount -> amount.getDate().getYear()));
        List<Integer> localDates = new ArrayList<>(collect.keySet());
        Collections.sort(localDates);
        List<SupplyDto> collect1 = localDates.stream()
                .map(collect::get)
                .map(amounts -> {
                    String year = String.valueOf(amounts.get(0).getDate().getYear()) + "년";
                    SupplyDto supplyDto = new SupplyDto(year);
                    Integer sum = amounts.stream()
                            .peek(amount -> supplyDto.addMap(amount.getBank().getInstituteName(), amount.getAmount()))
                            .mapToInt(Amount::getAmount).sum();

                    supplyDto.setMaxAmount(sum);
                    return supplyDto;
                })
                .collect(Collectors.toList());

        return new ResponseSupply("주택금융 공급현황", collect1);
    }

    @Transactional(readOnly = true)
    public ResponseMaxBankInYear statisticsMax(){

        Map<Integer, Map<String, Integer>> collect = this.amountRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(amount -> amount.getDate().getYear(),
                        Collectors.groupingBy(amount -> amount.getBank().getInstituteName(), summingInt(Amount::getAmount))));

        Comparator<Entry<String, Integer>> comparator1 = makeComparator();

        Entry<String, Integer> stringIntegerEntry = collect.keySet().stream()
                .map(collect::get)
                .map(stringIntegerMap ->
                        Collections.max(stringIntegerMap.entrySet(), comparator1))
                .findFirst().orElseGet(() -> new AbstractMap.SimpleEntry<String, Integer>("0",0));
        int max = -1;
        for (Integer collectKey : collect.keySet()){
            Map<String, Integer> stringIntegerMap = collect.get(collectKey);
            Boolean aBoolean = stringIntegerMap.entrySet().stream()
                    .map(stringIntegerEntry1 -> stringIntegerEntry1.getValue() == stringIntegerEntry.getValue()
                            && stringIntegerEntry1.getKey() == stringIntegerEntry.getKey()).findFirst()
                    .orElseGet(() -> false);
            if (aBoolean){
                max = collectKey;
                break ;
            }
        }

        return new ResponseMaxBankInYear(stringIntegerEntry.getKey(), max);
    }

    private Comparator<Entry<String, Integer>> makeComparator(){
        return new Comparator<Entry<String, Integer>>(){
            @Override
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        };
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseKEBInfo statisticsKEB() {
        log.info("Statistics KEB");
        Bank bank = this.bankRepository.findBankByInstituteCode(InstituteCode.KEB.getBankCode())
                .orElseThrow(() -> new NotFoundBankException(ExceptionCode.NOT_FOUND_BANK));

        Map<LocalDate, Double> averageByDate = this.amountRepository.findAmountsByBank(bank)
                .stream()
                .collect(Collectors.groupingBy(Amount::getDate))

                .entrySet().stream()
                .collect(Collectors.toMap(
                                Entry::getKey,
                                e -> e.getValue().stream()
                                        .map(Amount::getAmount)
                                        .mapToInt(Integer::intValue)
                                        .average().orElseGet(() -> -1)
                        )
                );


        LocalDate maxKey = Collections.max(averageByDate.entrySet(), Entry.comparingByValue()).getKey();
        LocalDate minKey = Collections.min(averageByDate.entrySet(), Entry.comparingByValue()).getKey();

        return new ResponseKEBInfo(InstituteCode.KEB.getBankName(),
                    List.of(
                            new ResponseKEBInfo.SupportAmount(minKey.getYear(), averageByDate.get(minKey).intValue()),
                            new ResponseKEBInfo.SupportAmount(maxKey.getYear(), averageByDate.get(maxKey).intValue())
                    )
                );
    }

}
