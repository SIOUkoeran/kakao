package com.example.kakao.service;

import com.example.kakao.model.Bank;
import org.springframework.stereotype.Service;

import java.time.Month;

@Service
public interface ModelService {
    public float predictAmount(String bank, String month);
}
