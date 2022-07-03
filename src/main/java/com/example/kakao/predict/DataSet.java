package com.example.kakao.predict;

import ai.djl.ndarray.NDArray;
import ai.djl.training.dataset.ArrayDataset;
import org.springframework.stereotype.Component;

@Component
public class DataSet {
        private static final int batchSize = 10;

        public ArrayDataset createDataSet(NDArray features, NDArray labels){
            return new ArrayDataset.Builder()
                    .setData(features)
                    .optLabels(labels)
                    .setSampling(batchSize, false)
                    .build();
        }
}
