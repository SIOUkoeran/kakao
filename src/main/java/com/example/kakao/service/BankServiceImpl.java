package com.example.kakao.service;


import com.example.kakao.dto.ResponseBank;
import com.example.kakao.dto.SupplyDto;
import com.example.kakao.model.Amount;
import com.example.kakao.repository.AmountRepository;
import com.example.kakao.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


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
    public List<SupplyDto> statisticsSupply() {
        Map<LocalDate, List<Amount>> collect = this.amountRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(Amount::getDate));
        Set<LocalDate> localDates = collect.keySet();

        List<SupplyDto> collect1 = localDates.stream()
                .map(collect::get)
                .map(amounts -> {
                    String year = String.valueOf(amounts.get(0).getDate().getYear()) + "년";
                    SupplyDto supplyDto = new SupplyDto(year);
                    Integer max = amounts.stream()
                            .peek(amount -> supplyDto.addMap(amount.getBank().getInstituteName(), amount.getAmount()))
                            .mapToInt(Amount::getAmount).max().getAsInt();
                    supplyDto.setMaxAmount(max);
                    return supplyDto;
                })
                .collect(Collectors.toList());
        return collect1;
    }
}
