package com.example.kakao.predict;

import com.example.kakao.model.Amount;
import com.example.kakao.model.Bank;
import com.example.kakao.repository.AmountRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DBLoaderImpl implements DBLoader{
    private final Logger logger = LoggerFactory.getLogger(DBLoaderImpl.class);

    private final AmountRepository amountRepository;

    public List<Amount> loadDB(Bank bank){
        return this.amountRepository.findAmountsByBank(bank)
                .stream().sorted(Comparator.comparing(Amount::getDate))
                .collect(Collectors.toList());
    }


}
