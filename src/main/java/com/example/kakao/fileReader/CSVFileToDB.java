package com.example.kakao.fileReader;

import com.example.kakao.api.ResponseFileSave;
import com.example.kakao.dto.CSVDto;
import com.example.kakao.model.Amount;
import com.example.kakao.model.Bank;
import com.example.kakao.repository.AmountRepository;
import com.example.kakao.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CSVFileToDB implements FileToDB {

    private final BankRepository bankRepository;
    private final AmountRepository amountRepository;
    private final CSV csv;


    @SneakyThrows
    @Override
    public ResponseFileSave saveCSVFile(MultipartFile multipartFile) {

        List<CSVDto> readLine = csv.readCSVFile(multipartFile);
        saveCsvDtoLists(readLine);
        return new ResponseFileSave();
    }

    private void saveCsvDtoLists(List<CSVDto> csvDtoLists){
        csvDtoLists
                .forEach(csvDto ->{
                    Bank bank =
                            bankRepository.findBankByInstituteCode(csvDto.getBankCode())
                                    .orElseGet(() -> this.bankRepository.save(new Bank(csvDto)));
                    this.amountRepository.save(new Amount(csvDto, bank));
                });
    }

    private Integer toInt(String s){
        return Integer.parseInt(s);
    }

}
