package com.example.kakao.fileReader;


import com.example.kakao.api.ResponseFileSave;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileToDB {
    public ResponseFileSave saveCSVFile(MultipartFile multipartFile);
}
