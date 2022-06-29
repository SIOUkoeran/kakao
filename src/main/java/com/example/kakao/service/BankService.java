package com.example.kakao.service;

import com.example.kakao.dto.ResponseBank;
import com.example.kakao.dto.ResponseKEBInfo;
import com.example.kakao.dto.ResponseMaxBankInYear;
import com.example.kakao.dto.ResponseSupply;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BankService {

    public List<ResponseBank> findAllBanks();
    public ResponseSupply statisticsSupply();
    public ResponseMaxBankInYear statisticsMax();
    public ResponseKEBInfo statisticsKEB();
}
