package com.example.kakao.api;


import com.example.kakao.dto.Response;
import com.example.kakao.dto.ResponseBank;
import com.example.kakao.dto.SupplyDto;
import com.example.kakao.model.Bank;
import com.example.kakao.repository.BankRepository;
import com.example.kakao.service.BankService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/bank")
public class BankController {

    private final BankService bankService;

    @GetMapping("/list")
    public Response getAllBanks(){
        List<ResponseBank> allBanks = this.bankService.findAllBanks();
        return Response.builder()
                .code("2000")
                .message("find All Banks")
                .data(allBanks)
                .build();
    }

    @GetMapping("/statistics/supply")
    public List<SupplyDto> statisticsSupply(){
        log.info("statistics/supply all Bank In All Time");
        return this.bankService.statisticsSupply();

    }
}
