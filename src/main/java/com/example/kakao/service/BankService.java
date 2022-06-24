package com.example.kakao.service;

import com.example.kakao.dto.ResponseBank;
import com.example.kakao.dto.SupplyDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BankService {

    public List<ResponseBank> findAllBanks();
    public List<SupplyDto> statisticsSupply();
}
