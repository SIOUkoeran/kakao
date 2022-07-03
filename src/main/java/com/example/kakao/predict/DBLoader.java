package com.example.kakao.predict;

import com.example.kakao.model.Amount;
import com.example.kakao.model.Bank;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DBLoader {
    public List<Amount> loadDB(Bank bank);
}
