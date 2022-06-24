package com.example.kakao.service;

import com.example.kakao.dto.ResponseBank;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BankService {

    public List<ResponseBank> findAllBanks();
}
