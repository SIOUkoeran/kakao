package com.example.kakao.predict;

import ai.djl.ndarray.NDArray;
import ai.djl.training.dataset.ArrayDataset;
import org.springframework.stereotype.Component;

@Component
public class ArrayLoader {

    public ArrayDataset loadArray(NDArray features, NDArray labels, int batchSize,
                                  boolean shuffle){
        return new ArrayDataset.Builder()
                .setData(features)
                .optLabels(labels)
                .setSampling(batchSize, shuffle)
                .build();

    }

}
