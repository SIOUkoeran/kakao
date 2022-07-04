package com.example.kakao.predict;

import com.example.kakao.exception.ExceptionCode;
import com.example.kakao.exception.NotFoundBankException;
import com.example.kakao.model.Amount;
import com.example.kakao.model.Bank;
import com.example.kakao.model.InstituteCode;
import com.example.kakao.repository.AmountRepository;
import com.example.kakao.repository.BankRepository;
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
    private final BankRepository bankRepository;
    public List<Amount> loadDB(String bank){
        return this.amountRepository.findAmountsByBank(
                    this.bankRepository.findBankByInstituteCode(InstituteCode.valueOf(bank).getBankCode())
                            .orElseThrow(() -> new NotFoundBankException(ExceptionCode.NOT_FOUND_BANK))
                )
                .stream().sorted(Comparator.comparing(Amount::getDate))
                .collect(Collectors.toList());
    }


}
