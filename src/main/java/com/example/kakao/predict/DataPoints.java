package com.example.kakao.predict;

import ai.djl.ndarray.NDArray;
import ai.djl.ndarray.NDManager;
import com.example.kakao.model.Amount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
public class DataPoints {

    private NDArray x, y;
    private static final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");

    public DataPoints(NDArray x, NDArray y) {
        this.x = x;
        this.y = y;
    }

    public NDArray getX() {
        return x;
    }

    public NDArray getY() {
        return y;
    }

    public static DataPoints syntheticData(NDManager manager, List<Amount> amounts){

        NDArray x = manager.create(amounts.stream()
                .map(Amount::getDate)
                        .map(localDate ->
                            String.valueOf(localDate.getYear()) + String.valueOf(localDate.getMonth()))
                        .map(Integer::valueOf)
                .mapToInt(value -> value)
                .toArray());
        NDArray y = manager.create(amounts.stream()
                .map(Amount::getAmount)
                .mapToInt(Integer::intValue)
                .toArray());
        return new DataPoints(x, y);
    }
}
