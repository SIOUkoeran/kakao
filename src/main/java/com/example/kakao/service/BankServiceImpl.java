package com.example.kakao.service;


import com.example.kakao.dto.ResponseBank;
import com.example.kakao.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;

    @Transactional(readOnly = true)
    public List<ResponseBank> findAllBanks(){
        return this.bankRepository.findAll().stream()
                .map(ResponseBank::new)
                .collect(Collectors.toList());
    }
}
