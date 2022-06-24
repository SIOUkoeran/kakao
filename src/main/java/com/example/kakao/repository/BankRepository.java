package com.example.kakao.repository;


import com.example.kakao.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

    boolean existsByInstituteCode(Integer code);
    Optional<Bank> findFirstByInstituteCode(Integer bankCode);
    Optional<Bank> findBankByInstituteCode(Integer bankCode);
}
