package com.example.kakao.repository;

import com.example.kakao.model.Amount;
import com.example.kakao.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AmountRepository extends JpaRepository<Amount, Long> {

    List<Amount> findAmountsByBank(Bank bank);
    List<Amount> findAmountsByDateBetween(LocalDate start, LocalDate end);

}
