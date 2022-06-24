package com.example.kakao.fileReader;

import com.example.kakao.dto.CSVDto;
import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class OpenCSV implements CSV {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-M");

    @Override
    public List<CSVDto> readCSVFile(MultipartFile multipartFile){
        InputStream inputStream = null;
        try {
            inputStream = multipartFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Reader reader = new BufferedReader(new InputStreamReader(inputStream));
        CSVReader csvReader = new CSVReader(reader);
        List<CSVDto> csvDtoList = new ArrayList<>();
        String[] line;
        try {
            line = csvReader.readNext();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true){
            try {
                if (((line = csvReader.readNext()) == null)) break;
                else {
                    for (int i = 2; i < line.length; i++){
                        csvDtoList.add(
                                new CSVDto(LocalDate.of(toInt(line[0]), toInt(line[1]),1),
                                        line[i].replaceAll(",",""), i)
                        );
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csvDtoList;
    }
    private Integer toInt(String s){
        return Integer.parseInt(s);
    }
}
