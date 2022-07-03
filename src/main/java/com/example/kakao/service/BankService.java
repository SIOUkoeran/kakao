package com.example.kakao.service;

import com.example.kakao.dto.ResponseBank;
import com.example.kakao.dto.ResponseKEBInfo;
import com.example.kakao.dto.ResponseMaxBankInYear;
import com.example.kakao.dto.ResponseSupply;
import com.example.kakao.model.Amount;
import com.example.kakao.model.Bank;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public interface BankService {

    public List<ResponseBank> findAllBanks();
    public ResponseSupply statisticsSupply();
    public ResponseMaxBankInYear statisticsMax();
    public ResponseKEBInfo statisticsKEB();
    public Map<LocalDate, List<Amount>> findAmountsByBank(Bank bank);
    public Bank findBank(String bankName);
}
