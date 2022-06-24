package com.example.kakao.fileReader;

import com.example.kakao.dto.CSVDto;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public interface CSV {
    public List<CSVDto> readCSVFile(MultipartFile multipartFile);
}
