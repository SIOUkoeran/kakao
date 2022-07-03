package com.example.kakao.model;


import com.example.kakao.dto.CSVDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Slf4j
@NoArgsConstructor
public class Amount {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer amount;

    @DateTimeFormat(pattern = "yyyy-MM")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private Bank bank;

    public Amount(Integer amount, LocalDate time, Bank bank) {
        this.amount = amount;
        this.date = time;
        this.bank = bank;
    }

    public void setBank(Bank bank){
        this.bank = bank;
        bank.getAmounts().add(this);
    }

    public Amount(CSVDto csvDto, Bank bank){
        this.amount = Integer.parseInt(csvDto.getAmount());
        this.date = csvDto.getDate();
        setBank(bank);
    }
}
