package com.example.kakao.service;

import com.example.kakao.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BankService {

    public List<ResponseBank> findAllBanks();
    public ResponseSupply statisticsSupply();
    public ResponseMaxBankInYear statisticsMax();
}
