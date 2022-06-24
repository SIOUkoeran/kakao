package com.example.kakao.api;

import com.example.kakao.dto.Response;
import com.example.kakao.fileReader.CSVFileToDB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("/file")
public class UploadController {

    private final CSVFileToDB csvFileToDB;

    public UploadController(CSVFileToDB csvFileToDB) {
        this.csvFileToDB = csvFileToDB;
    }

    @PostMapping("/csv")
    public Response saveCSVFileToDBApi(MultipartFile multipartFile) {
        if (!multipartFile.getOriginalFilename().contains(".csv")) {
            throw new EntityNotFoundException("file extension is not csv");
        }
        csvFileToDB.saveCSVFile(multipartFile);

        return Response.response()
                .message("save success")
                .code("2010")
                .response();
    }
}
