package com.example.kakao.api;


import com.example.kakao.dto.Response;
import com.example.kakao.dto.ResponseBank;
import com.example.kakao.dto.ResponseMaxBankInYear;
import com.example.kakao.dto.ResponseSupply;
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
    public ResponseSupply statisticsSupply(){
        log.info("statistics/supply all Bank In All Time");
        return this.bankService.statisticsSupply();
    }

    @GetMapping("/statistics/max")
    public ResponseMaxBankInYear statisticsFindMaxInYear(){
        return this.bankService.statisticsMax();
    }

    @GetMapping("/statistics/KEB")
    public Response statisticsFindKEBInfo(){

        return Response.builder()
                .code("2000")
                .message("statistics/KEB Average In Total")
                .data(this.bankService.statisticsKEB())
                .build();
    }
}
